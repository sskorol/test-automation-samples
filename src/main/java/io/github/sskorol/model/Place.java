package io.github.sskorol.model;

public class Place {

    private static int id = 1;
    private final int number;
    private final Clazz clazz;
    private boolean isAvailable;

    public Place(Clazz clazz) {
        this.number = id++;
        this.clazz = clazz;
        this.isAvailable = true;
    }

    public void book() {
        this.isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getNumber() {
        return number;
    }

    public Clazz getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "Place{" +
                "number = " + number +
                ", clazz=" + clazz +
                '}';
    }
}
