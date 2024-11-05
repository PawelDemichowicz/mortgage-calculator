package com.mortgage.service;

import com.mortgage.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReferenceCalculationServiceImplTest {
    private ReferenceCalculationService referenceCalculationService;

    @BeforeEach
    public void setup() {
        this.referenceCalculationService = new ReferenceCalculationServiceImpl();
    }

    @Test
    @DisplayName("Calculate mortgage reference for first rate")
    void shouldCalculateMortgageReferenceForFirstRateAmountsCorrectly() {
        // Given
        InputData inputData = TestData.someInputData();
        MortgageReference expected = TestData.someMortgageReference()
                .withReferenceAmount(new BigDecimal("350000"))
                .withReferenceDuration(new BigDecimal("180"));

        // When
        MortgageReference result = referenceCalculationService.calculate(inputData);

        // Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Calculate mortgage reference for reduce rates")
    void shouldReturnMortgageReferenceForReduceRate() {
        // Given
        InputData inputData = TestData.someInputData()
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);
        RateAmounts rateAmounts = TestData.someRateAmounts()
                .withOverpayment(TestData.someOverpayment());
        Rate rate = TestData.someRate()
                .withMortgageResidual(TestData.someMortgageResidual()
                        .withAmount(new BigDecimal("300000"))
                        .withDuration(new BigDecimal("150")));
        MortgageReference expected = TestData.someMortgageReference()
                .withReferenceAmount(new BigDecimal("288649.63"))
                .withReferenceDuration(new BigDecimal("149"));

        // When
        MortgageReference result = referenceCalculationService.calculate(inputData, rateAmounts, rate);

        // Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Calculate mortgage reference for reduce period")
    void shouldReturnMortgageReferenceForReducePeriod() {
        // Given
        InputData inputData = TestData.someInputData()
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD);
        RateAmounts rateAmounts = TestData.someRateAmounts();
        Rate rate = TestData.someRate();
        MortgageReference expected = TestData.someMortgageReference()
                .withReferenceAmount(new BigDecimal("350000"))
                .withReferenceDuration(new BigDecimal("180"));

        // When
        MortgageReference result = referenceCalculationService.calculate(inputData, rateAmounts, rate);

        // Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Thrown the mortgage exception during provide wrong reduce way")
    void shouldThrowMortgageExceptionForInvalidOverpaymentReduceWay() {
        // Given
        InputData inputData = TestData.someInputData()
                .withOverpaymentReduceWay("INVALID_WAY");
        RateAmounts rateAmounts = TestData.someRateAmounts();
        Rate rate = TestData.someRate();

        // When
        Executable executable = () -> referenceCalculationService.calculate(inputData, rateAmounts, rate);

        // Then
        assertThrows(MortgageException.class, executable);
    }
}