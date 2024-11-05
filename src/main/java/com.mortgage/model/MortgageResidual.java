package com.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;

@With
@Value
@Builder
@AllArgsConstructor
public class MortgageResidual {
    BigDecimal amount;
    BigDecimal duration;

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
