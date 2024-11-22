package com.mortgage.service;

import com.mortgage.fixtures.TestData;
import com.mortgage.model.InputData;
import com.mortgage.model.TimePoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TimePointServiceImplTest {

    private TimePointService timePointService;

    @BeforeEach
    public void setup() {
        this.timePointService = new TimePointServiceImpl();
    }

    @Test
    @DisplayName("Should calculate first rate time point successfully")
    void calculateTimePointForFirstRate() {
        // Given
        InputData inputData = TestData.someInputData();
        TimePoint timePoint = TestData.someTimePoint();

        // When
        TimePoint result = timePointService.calculate(BigDecimal.valueOf(1), inputData);

        // Then
        Assertions.assertEquals(timePoint, result);
    }
}