package com.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class TimePoint {
    LocalDate date;
    BigDecimal year;
    BigDecimal month;
}
