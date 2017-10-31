package io.github.sskorol.model;

import static io.github.sskorol.model.Clazz.FIRST;
import static io.github.sskorol.model.Clazz.SECOND;
import static io.github.sskorol.model.TrainType.INTERCITY;
import static io.vavr.API.*;

public final class PriceHolder {

    private PriceHolder() {
        throw new UnsupportedOperationException("Unable to access private constructor");
    }

    public static double compute(final TrainType type, final Clazz clazz) {
        return Match(clazz).of(
                Case($(FIRST), () -> type == INTERCITY ? 401.00 : 792.00),
                Case($(SECOND), () -> type == INTERCITY ? 249.00 : 334.00),
                Case($(), () -> type == INTERCITY ? 0.00 : 201.00)
        );
    }
}
