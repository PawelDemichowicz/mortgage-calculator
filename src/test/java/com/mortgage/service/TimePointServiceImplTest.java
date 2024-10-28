package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.RateType;
import com.mortgage.model.TimePoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class TimePointServiceImplTest {

    private TimePointService timePointService;

    @BeforeEach
    public void setup() {
        this.timePointService = new TimePointServiceImpl();
    }

    @Test
    @DisplayName("Should calculate first rate time point successfully")
    void calculateTimePointForFirstRate() {
        // given
        InputData inputData = someInputData();
        TimePoint timePoint = someTimePoint();

        // when
        TimePoint result = timePointService.calculate(BigDecimal.valueOf(1), inputData);

        // then
        Assertions.assertEquals(timePoint, result);

    }

    private InputData someInputData() {
        return InputData.builder()
                .repaymentStartDate(LocalDate.of(2010, 5, 10))
                .wiborPercent(new BigDecimal("2.73"))
                .amount(new BigDecimal("350000"))
                .monthsDuration(BigDecimal.valueOf(180))
                .rateType(RateType.CONSTANT)
                .bankMarginPercent(new BigDecimal("1.9"))
                .overpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .overpaymentProvisionPercent(BigDecimal.valueOf(3))
                .overpaymentProvisionMonths(BigDecimal.valueOf(36))
                .build();
    }

    private TimePoint someTimePoint() {
        return TimePoint.builder()
                .year(BigDecimal.valueOf(1))
                .month(BigDecimal.valueOf(1))
                .date(LocalDate.of(2010, 5, 10))
                .build();
    }
}