package com.mortgage.integration;

import com.mortgage.configuration.ApplicationConfiguration;
import com.mortgage.fixtures.TestData;
import com.mortgage.model.InputData;
import com.mortgage.model.Rate;
import com.mortgage.service.RateCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
public class RateCalculationServiceIT {

    @Autowired
    private RateCalculationService rateCalculationService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(rateCalculationService);
    }

    @Test
    @DisplayName("Calculate rates with integration services")
    void shouldCalculateRatesCorrectlyIT() {
        // Given
        InputData inputData = TestData.someInputData();

        // When
        List<Rate> result = rateCalculationService.calculate(inputData);

        // Then
        Assertions.assertEquals(180, result.size());
        Assertions.assertEquals(TestData.someRate5(), result.get(4));
        Assertions.assertEquals(TestData.someRate10(), result.get(9));
        Assertions.assertEquals(TestData.someRate40(), result.get(39));
        Assertions.assertEquals(TestData.someRate80(), result.get(79));

    }
}
