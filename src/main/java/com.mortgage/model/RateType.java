package com.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RateType {
    CONSTANT("CONSTANT"),
    DECREASING("DECREASING");

    private final String name;
}
