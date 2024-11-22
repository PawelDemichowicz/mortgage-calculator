package com.mortgage.fixtures;

import com.mortgage.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class TestData {

    public static InputData someInputData() {
        return InputData.builder()
                .repaymentStartDate(LocalDate.of(2010, 5, 10))
                .wiborPercent(new BigDecimal("2.73"))
                .amount(new BigDecimal("350000"))
                .monthsDuration(BigDecimal.valueOf(180))
                .rateType(RateType.CONSTANT)
                .bankMarginPercent(new BigDecimal("1.9"))
                .overpaymentSchema(Map.of())
                .overpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .overpaymentProvisionPercent(BigDecimal.valueOf(3))
                .overpaymentProvisionMonths(BigDecimal.valueOf(36))
                .build();
    }

    public static TimePoint someTimePoint() {
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

    public static Rate someRate5() {
        return Rate.builder()
                .rateNumber(BigDecimal.valueOf(5))
                .timePoint(TimePoint.builder()
                        .year(BigDecimal.ONE)
                        .month(BigDecimal.valueOf(5))
                        .date(LocalDate.of(2010, 9, 10))
                        .build())
                .rateAmounts(RateAmounts.builder()
                        .rateAmount(new BigDecimal("2700.79"))
                        .interestAmount(new BigDecimal("1329.46"))
                        .capitalAmount(new BigDecimal("1371.33"))
                        .overpayment(Overpayment.builder()
                                .amount(BigDecimal.ZERO)
                                .provisionAmount(BigDecimal.ZERO)
                                .build())
                        .build())
                .mortgageResidual(MortgageResidual.builder()
                        .amount(new BigDecimal("343195.85"))
                        .duration(new BigDecimal("175"))
                        .build())
                .mortgageReference(MortgageReference.builder()
                        .referenceAmount(new BigDecimal("350000"))
                        .referenceDuration(new BigDecimal("180"))
                        .build())
                .build();
    }

    public static Rate someRate10() {
        return Rate.builder()
                .rateNumber(BigDecimal.valueOf(10))
                .timePoint(TimePoint.builder()
                        .year(BigDecimal.ONE)
                        .month(BigDecimal.valueOf(10))
                        .date(LocalDate.of(2011, 2, 10))
                        .build())
                .rateAmounts(RateAmounts.builder()
                        .rateAmount(new BigDecimal("2700.79"))
                        .interestAmount(new BigDecimal("1302.79"))
                        .capitalAmount(new BigDecimal("1397.99"))
                        .overpayment(Overpayment.builder()
                                .amount(BigDecimal.ZERO)
                                .provisionAmount(BigDecimal.ZERO)
                                .build())
                        .build())
                .mortgageResidual(MortgageResidual.builder()
                        .amount(new BigDecimal("336259.41"))
                        .duration(new BigDecimal("170"))
                        .build())
                .mortgageReference(MortgageReference.builder()
                        .referenceAmount(new BigDecimal("350000"))
                        .referenceDuration(new BigDecimal("180"))
                        .build())
                .build();
    }

    public static Rate someRate40() {
        return Rate.builder()
                .rateNumber(BigDecimal.valueOf(40))
                .timePoint(TimePoint.builder()
                        .year(BigDecimal.valueOf(4))
                        .month(BigDecimal.valueOf(4))
                        .date(LocalDate.of(2013, 8, 10))
                        .build())
                .rateAmounts(RateAmounts.builder()
                        .rateAmount(new BigDecimal("2700.79"))
                        .interestAmount(new BigDecimal("1131.59"))
                        .capitalAmount(new BigDecimal("1569.20"))
                        .overpayment(Overpayment.builder()
                                .amount(BigDecimal.ZERO)
                                .provisionAmount(BigDecimal.ZERO)
                                .build())
                        .build())
                .mortgageResidual(MortgageResidual.builder()
                        .amount(new BigDecimal("291715.28"))
                        .duration(new BigDecimal("140"))
                        .build())
                .mortgageReference(MortgageReference.builder()
                        .referenceAmount(new BigDecimal("350000"))
                        .referenceDuration(new BigDecimal("180"))
                        .build())
                .build();
    }

    public static Rate someRate80() {
        return Rate.builder()
                .rateNumber(BigDecimal.valueOf(80))
                .timePoint(TimePoint.builder()
                        .year(BigDecimal.valueOf(7))
                        .month(BigDecimal.valueOf(8))
                        .date(LocalDate.of(2016, 12, 10))
                        .build())
                .rateAmounts(RateAmounts.builder()
                        .rateAmount(new BigDecimal("2700.79"))
                        .interestAmount(new BigDecimal("870.27"))
                        .capitalAmount(new BigDecimal("1830.52"))
                        .overpayment(Overpayment.builder()
                                .amount(BigDecimal.ZERO)
                                .provisionAmount(BigDecimal.ZERO)
                                .build())
                        .build())
                .mortgageResidual(MortgageResidual.builder()
                        .amount(new BigDecimal("223724.19"))
                        .duration(new BigDecimal("100"))
                        .build())
                .mortgageReference(MortgageReference.builder()
                        .referenceAmount(new BigDecimal("350000"))
                        .referenceDuration(new BigDecimal("180"))
                        .build())
                .build();
    }
}
