package io.github.sskorol.model;

import one.util.streamex.StreamEx;

import java.util.List;
import java.util.function.IntFunction;

import static io.github.sskorol.model.Clazz.FIRST;
import static io.github.sskorol.model.Clazz.SECOND;
import static io.github.sskorol.model.TrainType.INTERCITY;
import static io.vavr.API.*;
import static one.util.streamex.StreamEx.generate;

public interface Train {

    String number();

    TrainType type();

    List<TrainCarriage> carriages();

    List<Clazz> supportedClasses();

    default List<TrainCarriage> generateCarriage() {
        return StreamEx.of(supportedClasses())
                       .flatMap(clazz -> generate(() -> new TrainCarriage(clazz, carriageCapacity(clazz)))
                               .limit(carriagesAmount()))
                       .toList();
    }

    default int carriagesAmount() {
        return type() == INTERCITY ? 7 : 15;
    }

    default int carriageCapacity(final Clazz clazz) {
        final IntFunction<Integer> converter = baseAmount -> type() == INTERCITY ? baseAmount - 10 : baseAmount;
        return Match(clazz).of(
                Case($(FIRST), () -> converter.apply(40)),
                Case($(SECOND), () -> converter.apply(50)),
                Case($(), () -> converter.apply(60))
        );
    }
}
