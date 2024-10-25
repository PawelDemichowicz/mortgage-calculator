package com.mortgage.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

@Value
@With
@Builder
public class InputData {
    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);
    LocalDate repaymentStartDate;
    BigDecimal wiborPercent;
    BigDecimal amount;
    BigDecimal monthsDuration;
    RateType rateType;
    BigDecimal bankMarginPercent;
    Map<Integer, BigDecimal> overpaymentSchema;
    String overpaymentReduceWay;
    BigDecimal overpaymentProvisionPercent;
    BigDecimal overpaymentProvisionMonths;

    public static InputData defaultInputData() {
        return InputData.builder()
                .repaymentStartDate(LocalDate.of(2020, 1, 6))
                .wiborPercent(new BigDecimal("1.73"))
                .amount(new BigDecimal("300000"))
                .monthsDuration(BigDecimal.valueOf(180))
                .rateType(RateType.DECREASING)
                .bankMarginPercent(new BigDecimal("1.9"))
                .overpaymentSchema(Map.of(
                        5, BigDecimal.valueOf(10000),
                        6, BigDecimal.valueOf(10000),
                        7, BigDecimal.valueOf(10000),
                        8, BigDecimal.valueOf(10000))
                )
                .overpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .overpaymentProvisionPercent(BigDecimal.valueOf(3))
                .overpaymentProvisionMonths(BigDecimal.valueOf(3))
                .build();
    }

    public BigDecimal getInterestPercent() {
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }
}


