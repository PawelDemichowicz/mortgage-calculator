package com.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
@AllArgsConstructor
public class MortgageReference {
    BigDecimal referenceAmount;
    BigDecimal referenceDuration;
}
