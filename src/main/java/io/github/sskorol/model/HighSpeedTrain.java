package io.github.sskorol.model;

import java.util.List;
import java.util.UUID;

import static io.github.sskorol.model.Clazz.FIRST;
import static io.github.sskorol.model.Clazz.SECOND;
import static io.github.sskorol.model.TrainType.INTERCITY;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class HighSpeedTrain implements Train, Cloneable {

    private final String id;
    private final List<TrainCarriage> carriages;

    public HighSpeedTrain() {
        this.id = UUID.randomUUID().toString();
        this.carriages = generateCarriage();
    }

    @Override
    public String number() {
        return id;
    }

    @Override
    public TrainType type() {
        return INTERCITY;
    }

    @Override
    public List<TrainCarriage> carriages() {
        return unmodifiableList(carriages);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public List<Clazz> supportedClasses() {
        return asList(FIRST, SECOND);
    }
}
