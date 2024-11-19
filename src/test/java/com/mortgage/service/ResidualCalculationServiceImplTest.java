package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.MortgageResidual;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ResidualCalculationServiceImplTest {

    private ResidualCalculationService residualCalculationService;

    @BeforeEach
    public void setup() {
        this.residualCalculationService = new ResidualCalculationServiceImpl();
    }

    @Test
    @DisplayName("Calculate mortgage residual for first rate")
    void shouldCalculateMortgageResidualForFirstRateAmountsCorrectly() {
        // Given
        InputData inputData = TestData.someInputData();
        RateAmounts rateAmounts = TestData.someRateAmounts()
                .withOverpayment(TestData.someOverpayment());
        MortgageResidual expected = TestData.someMortgageResidual()
                .withAmount(new BigDecimal("338649.63"))
                .withDuration(new BigDecimal("179"));

        // When
        MortgageResidual result = residualCalculationService.calculate(rateAmounts, inputData);

        // Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Calculate mortgage residual for other rates")
    void shouldCalculateMortgageResidualForOtherRateAmountsCorrectly() {
        // Given
        RateAmounts rateAmounts = TestData.someRateAmounts()
                .withOverpayment(TestData.someOverpayment());
        Rate previousRate = TestData.someRate()
                .withMortgageResidual(TestData.someMortgageResidual()
                        .withAmount(new BigDecimal("220000")));
        MortgageResidual expected = TestData.someMortgageResidual()
                .withAmount(new BigDecimal("208649.63"))
                .withDuration(new BigDecimal("19"));

        // When
        MortgageResidual result = residualCalculationService.calculate(rateAmounts, previousRate);

        // Then
        Assertions.assertEquals(expected, result);
    }
}