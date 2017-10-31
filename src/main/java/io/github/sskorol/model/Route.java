package io.github.sskorol.model;

import java.time.LocalDateTime;

import static io.github.sskorol.utils.DateTimeUtils.format;

public class Route {

    private City departureCity;
    private City destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;

    public Route(City departureCity, City destinationCity, LocalDateTime departureTime, LocalDateTime destinationTime) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.destinationTime = destinationTime;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getDestinationTime() {
        return destinationTime;
    }

    @Override
    public String toString() {
        return "{departureCity=" + departureCity +
                ", destinationCity=" + destinationCity +
                ", departureTime=" + format(departureTime) +
                ", destinationTime=" + format(destinationTime) +
                '}';
    }
}
