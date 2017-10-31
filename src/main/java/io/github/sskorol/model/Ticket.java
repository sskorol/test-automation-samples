package io.github.sskorol.model;

import static java.util.Objects.nonNull;

public class Ticket {

    private String username;
    private String trainId;
    private int carriageNumber;
    private Clazz carriageClass;
    private int placeNumber;
    private Route route;
    private double price;

    public boolean hasTrainNumber() {
        return nonNull(trainId);
    }

    public boolean hasTrainCarriageInfo() {
        return nonNull(carriageClass) && carriageNumber > 0;
    }

    public boolean hasTrainInfo() {
        return hasTrainNumber() && hasTrainCarriageInfo();
    }

    public boolean hasRequiredBookingInfo() {
        return hasTrainInfo() && nonNull(route) && nonNull(username) && placeNumber > 0 && price > 0.00;
    }

    public void setCarriageNumber(final int number) {
        this.carriageNumber = number;
    }

    public Ticket withTrainId(final String trainId) {
        this.trainId = trainId;
        return this;
    }

    public Ticket withCarriageNumber(final int number) {
        this.carriageNumber = number;
        return this;
    }

    public Ticket withCarriageClass(final Clazz carriageClass) {
        this.carriageClass = carriageClass;
        return this;
    }

    public Ticket withRoute(final Route route) {
        this.route = route;
        return this;
    }

    public Ticket withOwner(final String owner) {
        this.username = owner;
        return this;
    }

    public Ticket withPrice(final double price) {
        this.price = price;
        return this;
    }

    public Ticket withPlaceNumber(final int placeNumber) {
        this.placeNumber = placeNumber;
        return this;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getUsername() {
        return username;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public Clazz getCarriageClass() {
        return carriageClass;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public Route getRoute() {
        return route;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "username='" + username + '\'' +
                ", trainId='" + trainId + '\'' +
                ", carriageNumber=" + carriageNumber +
                ", carriageClass=" + carriageClass +
                ", placeNumber=" + placeNumber +
                ", route=" + route +
                ", price=" + price +
                '}';
    }
}
