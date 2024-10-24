package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {
    TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
