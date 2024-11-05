package com.mortgage.service;

import com.mortgage.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestData {

    static InputData someInputData() {
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

    static TimePoint someTimePoint() {
        return TimePoint.builder()
                .year(BigDecimal.valueOf(1))
                .month(BigDecimal.valueOf(1))
                .date(LocalDate.of(2010, 5, 10))
                .build();
    }

    public static RateAmounts someRateAmounts() {
        return RateAmounts.builder()
                .rateAmount(new BigDecimal("2700.79"))
                .interestAmount(new BigDecimal("1350.42"))
                .capitalAmount(new BigDecimal("1350.37"))
                .build();
    }

    public static Rate someRate() {
        return Rate.builder()
                .timePoint(someTimePoint())
                .mortgageResidual(someMortgageResidual())
                .mortgageReference(someMortgageReference())
                .build();
    }

    public static MortgageResidual someMortgageResidual() {
        return MortgageResidual.builder()
                .amount(new BigDecimal("1233"))
                .duration(BigDecimal.valueOf(20))
                .build();
    }

    public static MortgageReference someMortgageReference() {
        return MortgageReference.builder()
                .referenceAmount(new BigDecimal("20222"))
                .referenceDuration(BigDecimal.valueOf(20))
                .build();
    }

    public static Overpayment someOverpayment() {
        return Overpayment.builder()
                .amount(new BigDecimal("10000"))
                .provisionAmount(new BigDecimal("300"))
                .build();
    }
}
