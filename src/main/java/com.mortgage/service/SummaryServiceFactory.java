package com.mortgage.service;

import com.mortgage.model.Rate;
import com.mortgage.model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {
    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(
                    rates,
                    rate -> rate.getRateAmounts().getInterestAmount()
            );
            BigDecimal provisionSum = calculate(
                    rates,
                    rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount()
            );
            BigDecimal totalLost = interestSum.add(provisionSum);
            return new Summary(interestSum, provisionSum, totalLost);
        };
    }

    private static BigDecimal calculate(List<Rate> rates, Function function) {
        return rates.stream()
                .reduce(BigDecimal.ZERO, (sum, next) -> sum.add(function.calculate(next)), BigDecimal::add);
    }
}
