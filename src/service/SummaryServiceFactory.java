package service;

import model.Rate;
import model.Summary;

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
        BigDecimal sum = BigDecimal.ZERO;
        for (Rate rate : rates) {
            sum = sum.add(function.calculate(rate));
        }
        return sum;
    }
}
