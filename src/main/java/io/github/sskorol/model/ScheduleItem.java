package io.github.sskorol.model;

import one.util.streamex.StreamEx;

import java.time.Duration;
import java.time.LocalDate;

import static io.github.sskorol.utils.DateTimeUtils.format;
import static java.util.stream.Collectors.counting;

public class ScheduleItem {

    private Route route;
    private Train train;

    public ScheduleItem(Train train, Route route) {
        this.route = route;
        this.train = train;
    }

    public boolean matches(City departureCity, City destinationCity, LocalDate departureDate) {
        return route.getDepartureCity() == departureCity
                && route.getDestinationCity() == destinationCity
                && route.getDepartureTime().toLocalDate().isEqual(departureDate);
    }

    public String getTrainId() {
        return train.number();
    }

    public Train getTrain() {
        return train;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Train #" + getTrainId() + "\n"
                + "Type: " + train.type() + "\n"
                + "Direction: " + route.getDepartureCity() + " - " + route.getDestinationCity() + "\n"
                + "Departure time: " + format(route.getDepartureTime()) + "\n"
                + "Route length: " + Duration.between(route.getDepartureTime(), route.getDestinationTime())
                                             .toMinutes() + " min\n"
                + "Destination time: " + format(route.getDestinationTime()) + "\n"
                + "Places:\n" + StreamEx.of(train.carriages())
                                        .flatMap(trainCarriage -> trainCarriage.getPlaces().stream())
                                        .filter(Place::isAvailable)
                                        .groupingBy(Place::getClazz, counting()) + "\n";
    }
}
