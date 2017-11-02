package com.travel.pay.model;

import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Preference {
    private final int numberOfStar;
    private final boolean swimmingPool;
    private final boolean tennis;
    private final boolean buffet;
    private final boolean allInclusive;

    public Preference() {
        this.numberOfStar = nextInt(3, 6);
        this.swimmingPool = nextBoolean();
        this.tennis = nextBoolean();
        this.buffet = nextBoolean();
        this.allInclusive = nextBoolean();
    }

    public int getNumberOfStar() {
        return numberOfStar;
    }

    private String isSwimmingPool() {
        String res;
        if (!swimmingPool) {
            res = "no";
        } else {
            res = "yes";
        }
        return res;
    }

    private String isTennis() {
        String res;
        if (!tennis) {
            res = "no";
        } else {
            res = "yes";
        }
        return res;
    }

    private String isBuffet() {
        String res;
        if (!buffet) {
            res = "no";
        } else {
            res = "yes";
        }
        return res;
    }

    private String isAllInclusive() {
        String res;
        if (!allInclusive) {
            res = "no";
        } else {
            res = "yes";
        }
        return res;
    }

    @Override
    public String toString() {
        return "Stars: " + numberOfStar +
                ", Swimming Pool: " + isSwimmingPool() +
                ", Tennis: " + isTennis() +
                ", Buffet: " + isBuffet() +
                ", All-Inclusive: " + isAllInclusive();
    }
}
