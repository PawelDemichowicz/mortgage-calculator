package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class OverpaymentCalculateServiceImplTest {
    private final OverpaymentCalculateService overpaymentCalculateService = new OverpaymentCalculateServiceImpl();

    @Test
    @DisplayName("Calculate single overpayment data")
    void shouldCalculateSingleOverpayment() {
        // Given
        BigDecimal rateNumber = BigDecimal.valueOf(8);
        InputData inputData = TestData.someInputData()
                .withOverpaymentSchema(Map.of(
                        5, BigDecimal.valueOf(10000),
                        8, BigDecimal.valueOf(10000))
                );
        Overpayment expected = TestData.someOverpayment();

        // When
        Overpayment result = overpaymentCalculateService.calculation(rateNumber, inputData);

        // Then
        Assertions.assertEquals(expected, result);
    }
}