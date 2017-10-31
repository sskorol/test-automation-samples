package io.github.sskorol.model;

import java.util.List;
import java.util.UUID;

import static io.github.sskorol.model.Clazz.*;
import static io.github.sskorol.model.TrainType.GRANY_TRAIN;
import static java.util.Arrays.asList;

public class CommonTrain implements Train {

    private final String id;
    private final List<TrainCarriage> carriages;

    public CommonTrain() {
        this.id = UUID.randomUUID().toString();
        this.carriages = generateCarriage();
    }

    @Override
    public String number() {
        return id;
    }

    @Override
    public TrainType type() {
        return GRANY_TRAIN;
    }

    @Override
    public List<TrainCarriage> carriages() {
        return carriages;
    }

    @Override
    public List<Clazz> supportedClasses() {
        return asList(FIRST, SECOND, THIRD);
    }
}
