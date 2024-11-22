package com.mortgage.service;

import com.mortgage.fixtures.TestData;
import com.mortgage.model.InputData;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class DecreasingAmountsCalculationServiceImplTest {

    private DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    @BeforeEach
    public void setup() {
        this.decreasingAmountsCalculationService = new DecreasingAmountsCalculationServiceImpl();
    }

    @Test
    @DisplayName("Calculate rate amounts for first rate")
    void shouldCalculateFirstRateAmountsCorrectly() {
        // Given
        InputData inputData = TestData.someInputData();
        RateAmounts expected = TestData.someRateAmounts()
                .withRateAmount(new BigDecimal("3294.86"))
                .withCapitalAmount(new BigDecimal("1944.44"));

        // When
        RateAmounts result = decreasingAmountsCalculationService.calculate(inputData, null);

        // Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Calculate rate amounts for other rates")
    void shouldCalculateOtherRateAmountsCorrectly() {
        // Given
        InputData inputData = TestData.someInputData();
        Rate rate = TestData.someRate();
        RateAmounts expected = TestData.someRateAmounts()
                .withRateAmount(new BigDecimal("1015.86"))
                .withInterestAmount(new BigDecimal("4.76"))
                .withCapitalAmount(new BigDecimal("1011.10"));

        // When
        RateAmounts result = decreasingAmountsCalculationService.calculate(inputData, null, rate);

        // Then
        Assertions.assertEquals(expected, result);
    }
}