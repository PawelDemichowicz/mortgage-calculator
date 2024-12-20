package com.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
@Builder
@AllArgsConstructor
public class Overpayment {
    public static final String REDUCE_RATE = "REDUCE_RATE";
    public static final String REDUCE_PERIOD = "REDUCE_PERIOD";

    BigDecimal amount;
    BigDecimal provisionAmount;

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getProvisionAmount() {
        return provisionAmount.setScale(2, RoundingMode.HALF_UP);
    }
}

