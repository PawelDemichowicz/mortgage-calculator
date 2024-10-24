package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.MortgageResidual;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;

public interface ResidualCalculationService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);
}
