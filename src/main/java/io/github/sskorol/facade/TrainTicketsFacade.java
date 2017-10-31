package io.github.sskorol.facade;

import io.github.sskorol.model.*;
import io.github.sskorol.service.PayPal;
import io.github.sskorol.service.PaymentService;
import io.vavr.Tuple;
import one.util.streamex.StreamEx;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static io.github.sskorol.model.City.Kharkov;
import static io.github.sskorol.model.City.Kiev;
import static io.github.sskorol.model.PaymentStatus.*;
import static io.github.sskorol.model.PriceHolder.compute;
import static io.vavr.API.*;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class TrainTicketsFacade {

    private List<ScheduleItem> schedule;
    private final AtomicInteger hour = new AtomicInteger(0);
    private Ticket currentTicket;
    private User currentUser;
    private List<User> users;
    private final PaymentService paymentService;
    private static final String TICKETS_UA_ACCOUNT_NUMBER = "1234567890";

    private final List<Employee> employees;

    public TrainTicketsFacade() {

        this.employees = asList(new Employee(), new Employee());


        final LocalDateTime now = LocalDateTime.of(2017, 10, 11, 7, 0);
        this.schedule = StreamEx.of(new HighSpeedTrain(), new CommonTrain())
                                .map(train -> new ScheduleItem(train,
                                        new Route(Kharkov,
                                                Kiev,
                                                now.plusHours(getSeqHour()),
                                                now.plusHours(getSeqHour()))))
                                .toList();
        this.currentTicket = new Ticket();
        this.users = asList(
                new User("Sergey", "Korol", "qaa", "123"),
                new User("Vitaliy", "Pupkin", "qa", "123"));
        this.paymentService = new PayPal();
    }

    public Employee findEmployee(final Predicate<Employee> condition) {
        return StreamEx.of(employees).findFirst(condition).orElseThrow(() -> new IllegalStateException(""));
    }

    public void loginWith(final String username, final String password) {
        this.currentUser = StreamEx.of(users)
                                   .findFirst(user -> user.getUsername().equals(username) && user.getPassword()
                                                                                                 .equals(password))
                                   .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        System.out.println("Logged in with " + currentUser.getFullName());
    }

    public void showSchedule() {
        checkUser();
        schedule.forEach(System.out::println);
    }

    public void showCarriages(final String id) {
        checkUser();

        this.currentTicket.withTrainId(id);
        StreamEx.of(schedule)
                .map(ScheduleItem::getTrain)
                .filter(train -> train.number().equals(id))
                .flatMap(train -> train.carriages().stream())
                .mapToEntry(trainCarriage -> StreamEx.of(trainCarriage.getPlaces()).filter(Place::isAvailable).count())
                .filterValues(placesAmount -> placesAmount > 0)
                .mapKeyValue(Tuple::of)
                .groupingBy(pair -> pair._1.getClazz(),
                        mapping(pair -> Tuple.of(pair._1.getNumber(), pair._2), toList()))
                .forEach((clazz, numAndPlaces) -> System.out.println("Carriage - " + clazz.name().toLowerCase()
                        + " class (" + computePrice(clazz) + " грн.): " + numAndPlaces));
    }

    public void showAvailablePlaces(final Clazz carriageClass, final int carriageNumber) {
        checkUser();

        if (isNull(currentTicket.getTrainId())) {
            System.out.println("You should select a train first.");
            return;
        }

        this.currentTicket.withCarriageNumber(carriageNumber).withCarriageClass(carriageClass);

        System.out.println("Available places: "
                + getAvailablePlaces(carriageClass, carriageNumber)
                .map(Place::getNumber)
                .joining(", "));
    }

    public void buyTicket(final String cardNumber, final YearMonth expirationDate, final String cvv) {
        checkUser();

        if (!currentTicket.hasRequiredBookingInfo()) {
            System.out.println("You have to provide all required information before ticket purchasing." +
                    " Current ticket info: " + currentTicket);
            return;
        }

        final Payment payment = new Payment()
                .withTargetAccountNumber(TICKETS_UA_ACCOUNT_NUMBER)
                .withDescription("Train Tickets")
                .withAmount(currentTicket.getPrice())
                .withPayerInfo(cardNumber, expirationDate, cvv);

        Match(paymentService.transfer(payment)).of(
                Case($(CHARGED), () -> run(() -> {
                    System.out.println("Bought the following ticket:\n" + currentTicket);
                    currentTicket = new Ticket();
                })),
                Case($(INSUFFICIENT_FUNDS),
                        () -> run(() -> System.out.println("Insufficient funds. Unable to buy a ticket:\n" + currentTicket))),
                Case($(INVALID_PAYER_CREDIT_CARD),
                        () -> run(() -> System.out.println("Invalid payer credit card data. Unable to buy a ticket:\n" + currentTicket))),
                Case($(INVALID_RECEIVER_CREDIT_CARD),
                        () -> run(() -> System.out.println("Invalid receiver account data. Unable to buy a ticket:\n" + currentTicket))),
                Case($(), () -> run(() -> System.out.println("Unknown error occurred. Unable to buy a ticket:\n" + currentTicket)))
        );
    }

    public void bookPlace(final int placeNumber, final String passengerName) {
        checkUser();

        if (!currentTicket.hasTrainInfo()) {
            System.out.println("You have to provide a valid train number, carriage class and carriage number.");
            return;
        } else if (!getAvailablePlaces(currentTicket.getCarriageClass(), currentTicket.getCarriageNumber())
                .map(Place::getNumber)
                .has(placeNumber)) {
            System.out.println("#" + placeNumber + " place is already booked.");
            return;
        }

        bookPlace(placeNumber);
        System.out.println("Booked: " + currentTicket.withOwner(passengerName)
                                                     .withPlaceNumber(placeNumber)
                                                     .withPrice(computePrice(currentTicket.getCarriageClass()))
                                                     .withRoute(getRoute()));
    }

    private void bookPlace(final int placeNumber) {
        getPlaces(currentTicket.getCarriageClass(), currentTicket.getCarriageNumber())
                .findFirst(place -> place.getNumber() == placeNumber)
                .ifPresent(Place::book);
    }

    private StreamEx<Place> getPlaces(final Clazz clazz, final int number) {
        return StreamEx.of(schedule)
                       .map(ScheduleItem::getTrain)
                       .filter(train -> train.number().equals(currentTicket.getTrainId()))
                       .flatMap(train -> train.carriages().stream())
                       .filter(trainCarriage -> trainCarriage.getClazz() == clazz && trainCarriage.getNumber() == number)
                       .flatMap(trainCarriage -> trainCarriage.getPlaces().stream());
    }

    private StreamEx<Place> getAvailablePlaces(final Clazz clazz, final int number) {
        return getPlaces(clazz, number).filter(Place::isAvailable);
    }

    private Route getRoute() {
        return StreamEx.of(schedule)
                       .findFirst(scheduleItem -> scheduleItem.getTrain().number().equals(currentTicket.getTrainId()))
                       .map(ScheduleItem::getRoute)
                       .orElseThrow(() -> new IllegalArgumentException("Unable to find route for train #"
                               + currentTicket.getTrainId()));
    }

    private double computePrice(final Clazz clazz) {
        return compute(StreamEx.of(schedule)
                               .map(ScheduleItem::getTrain)
                               .findFirst(train -> train.number().equals(currentTicket.getTrainId()))
                               .map(Train::type)
                               .orElseThrow(() -> new IllegalArgumentException("Unable to find train #"
                                       + currentTicket.getTrainId())), clazz);
    }

    private int getSeqHour() {
        final int currentHour = hour.get() < 24 ? hour.incrementAndGet() : 0;
        if (currentHour == 0) {
            hour.set(0);
        }
        return currentHour;
    }

    private void checkUser() {
        if (isNull(currentUser)) {
            throw new IllegalStateException("Unauthorized");
        }
    }
}
