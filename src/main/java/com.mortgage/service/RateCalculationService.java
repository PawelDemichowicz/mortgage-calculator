package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Rate;

import java.util.List;

public interface RateCalculationService {
    List<Rate> calculate(final InputData inputData);
}
