package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;

public interface DecreasingAmountsCalculationService {
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
