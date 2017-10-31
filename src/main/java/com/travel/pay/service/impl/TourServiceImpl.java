package com.travel.pay.service.impl;

import com.travel.pay.model.Tour;
import com.travel.pay.model.TourOperator;
import com.travel.pay.service.api.TourService;
import one.util.streamex.StreamEx;

import java.util.List;

import static java.util.Arrays.asList;

public class TourServiceImpl implements TourService {
    private final List<TourOperator> tourOperators;

    public TourServiceImpl() {
        this.tourOperators = asList(
                new TourOperator("Volya", "Kharkiv"),
                new TourOperator("SKY", "Kiev"),
                new TourOperator("Sun", "Odessa"));
    }

    @Override
    public List<Tour> getAllTours() {
        return StreamEx.of(tourOperators)
                .flatMap(tourOperator -> tourOperator.getTours().stream())
                .sorted()
                .distinct()
                .toList();
    }
}
