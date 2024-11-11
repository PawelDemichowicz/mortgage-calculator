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


