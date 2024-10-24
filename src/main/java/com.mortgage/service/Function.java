package com.mortgage.service;

import com.mortgage.model.Rate;

import java.math.BigDecimal;

public interface Function {
    BigDecimal calculate(Rate rate);
}
