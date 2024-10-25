package com.mortgage.model;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class MortgageResidual {
    BigDecimal amount;
    BigDecimal duration;

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
