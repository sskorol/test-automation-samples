package com.travel.pay.facade;

import com.travel.pay.model.Hotel;
import com.travel.pay.model.Tour;
import com.travel.pay.service.api.PaymentService;
import com.travel.pay.service.impl.PaymentServiceImpl;
import com.travel.pay.service.impl.TourServiceImpl;
import com.travel.pay.utils.DateTimeUtils;
import one.util.streamex.StreamEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelTourFacade {
    private final PaymentService paymentService;
    private final List<Tour> currentToursList;
    private final List<Tour> intermediateToursList = new ArrayList<>();
    private Tour currentTour;

    public TravelTourFacade() {
        this.paymentService = new PaymentServiceImpl();
        this.currentToursList = new TourServiceImpl().getAllTours();
    }

    public void buyTour(String firstName, String lastName, String cardNumber, String expirationDate, String cvv) {
        paymentService.payment(firstName, lastName, cardNumber, DateTimeUtils.formatCreditCard(expirationDate), cvv, currentTour.getPrice());
        currentToursList.retainAll(intermediateToursList);

        System.out.println(currentTour.withShortDescription());
    }

    public void getToursByDate(String tourDate) {

        final List<Tour> tours = StreamEx.of(currentToursList)
                .filter(tour -> tour.getFromDate().isEqual(DateTimeUtils.formatTourDate(tourDate)))
                .toList();

        StreamEx.of(tours)
                .forEach(tour -> System.out.println(tour.withShortDescription()));

        if (intermediateToursList != null) {
            intermediateToursList.clear();
            intermediateToursList.addAll(tours);
        } else {
            intermediateToursList.addAll(tours);
        }
    }

    public void getToursByPrice(double price) {
        final List<Tour> tours = StreamEx.of(intermediateToursList)
                .filter(tour -> tour.getPrice() <= price)
                .toList();

        StreamEx.of(tours)
                .forEach(tour -> System.out.println(tour.withShortDescription()));

        if (intermediateToursList != null) {
            intermediateToursList.clear();
            intermediateToursList.addAll(tours);
        } else {
            intermediateToursList.addAll(tours);
        }
    }

    public void selectTour(int id) {
        final Optional<Tour> selectedTour = StreamEx.of(intermediateToursList)
                .filter(tour -> tour.tourId() == id)
                .findAny();

        if (!selectedTour.isPresent()) {
            System.out.println("Incorrect id number of tour");
        } else {
            selectedTour.ifPresent(System.out::println);
            currentTour = selectedTour.get();
        }
    }

    public void getCurrentRoomType() {
        System.out.println(currentTour.getHotel().getAvailableRoomType());
    }

    public void selectRoomType(String typeRoom) {
        Hotel.RoomType selectedRoomType = Hotel.RoomType.valueOf(typeRoom.toUpperCase());

        final Optional<Hotel.RoomType> tR = StreamEx.of(Hotel.RoomType.values())
                .filter(type -> type.equals(selectedRoomType))
                .findFirst();

        if (!tR.isPresent()) {
            System.out.println("Inputted type not found");
        } else {
            currentTour.getHotel().bookTypeRoom(typeRoom);
            System.out.println(currentTour.withFinalDescription(typeRoom));
        }
    }

    public void bookTour(String clientName) {
        if (currentTour == null) {
            System.out.println("No current tour. Please restart");
        } else {
            currentTour.book();
            System.out.println("Client name: " + clientName + "\n" + "Booked: " + currentTour.withShortDescription());
        }
    }

}
