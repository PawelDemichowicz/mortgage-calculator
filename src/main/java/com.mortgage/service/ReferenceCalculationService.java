package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.MortgageReference;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;

public interface ReferenceCalculationService {
    MortgageReference calculate(InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
