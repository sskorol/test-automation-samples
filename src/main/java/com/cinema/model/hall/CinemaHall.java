package com.cinema.model.hall;

import com.cinema.model.Place;
import one.util.streamex.StreamEx;

import java.util.List;
import java.util.stream.Stream;

import static com.cinema.model.PlaceType.USUAL;
import static com.cinema.model.PlaceType.VIP;

public abstract class CinemaHall {
    public abstract String id();

    public abstract HallType hallType();

    public abstract List<Place> getPlaces();

    Stream<Place> generatePlaces() {
        return (Stream.concat(
                StreamEx.iterate(1, i -> i + 1)
                        .limit(rowsAmount(hallType()) - 1)
                        .flatMap(i -> StreamEx.iterate(1, j -> j + 1).limit(usualSeatsInRowAmount(hallType()))
                                .map(j -> new Place(USUAL, i, j))),
                StreamEx.iterate(1, i -> i + 1)
                        .limit(1)
                        .flatMap(i -> StreamEx.iterate(1, j -> j + 1).limit(vipSeatsInRowAmount(hallType()))
                                .map(j -> new Place(VIP, i, j)))

        ));
    }

    private int rowsAmount(HallType hallType) {
        switch (hallType) {
            case IMAX:
                return 5;
            case _2D:
                return 7;
            case _3D:
                return 3;
            case _4DX:
            default:
                return 4;
        }
    }

    private int usualSeatsInRowAmount(HallType hallType) {
        switch (hallType) {
            case IMAX:
                return 10;
            case _2D:
                return 8;
            case _3D:
                return 9;
            case _4DX:
            default:
                return 6;
        }
    }

    private int vipSeatsInRowAmount(HallType hallType) {
        switch (hallType) {
            case IMAX:
                return 5;
            case _2D:
                return 4;
            case _3D:
                return 7;
            case _4DX:
            default:
                return 3;
        }
    }
}
