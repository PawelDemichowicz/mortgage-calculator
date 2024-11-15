package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.TimePoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class TimePointServiceImpl implements TimePointService {
    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public TimePoint calculate(BigDecimal rateNumber, InputData inputData) {

        LocalDate date = calculateDate(rateNumber, inputData);
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);
        return new TimePoint(date, year, month);
    }

    private LocalDate calculateDate(BigDecimal rateNumber, InputData inputData) {
        return inputData.getRepaymentStartDate().plusMonths(rateNumber.subtract(BigDecimal.ONE).intValue());
    }

    private BigDecimal calculateYear(final BigDecimal rateNumber) {
        return rateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private BigDecimal calculateMonth(final BigDecimal rateNumber) {
        return BigDecimal.ZERO.equals(rateNumber.remainder(YEAR)) ? YEAR : rateNumber.remainder(YEAR);
    }
}
