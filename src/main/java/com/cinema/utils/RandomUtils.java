package com.cinema.utils;

import java.util.Random;

public class RandomUtils {
    private RandomUtils() {
        throw new UnsupportedOperationException("Illegal access to the private constructor");
    }

    public static int random(final int bound) {
        return new Random().nextInt(bound);
    }

}
