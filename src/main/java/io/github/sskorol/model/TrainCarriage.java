package io.github.sskorol.model;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TrainCarriage {

    private static int id = 1;
    private final int number;
    private final List<Place> places;
    private final Clazz clazz;

    public TrainCarriage(final Clazz clazz, final int places) {
        this.number = id++;
        this.clazz = clazz;
        this.places = Stream.generate(() -> new Place(clazz)).limit(places).collect(toList());
    }

    public List<Place> getPlaces() {
        return places;
    }

    public int getNumber() {
        return number;
    }

    public Clazz getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "TrainCarriage{" +
                "number = " + number +
                ", places=" + places +
                ", clazz=" + clazz +
                '}';
    }
}
