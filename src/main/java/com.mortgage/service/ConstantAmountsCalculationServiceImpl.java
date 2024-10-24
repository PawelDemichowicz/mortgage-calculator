package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantAmountsCalculationServiceImpl implements ConstantAmountsCalculationService {
    private static final BigDecimal YEAR = BigDecimal.valueOf(12);


    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal q = calculateQ(interestPercent);

        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthsDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmounts = calculateConstantRateAmounts(
                q,
                residualAmount,
                referenceDuration,
                interestAmount,
                referenceAmount
        );
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmounts, interestAmount, residualAmount);

        return new RateAmounts(rateAmounts, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal q = calculateQ(interestPercent);

        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmounts = calculateConstantRateAmounts(
                q,
                referenceAmount,
                referenceDuration,
                interestAmount,
                residualAmount
        );
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmounts, interestAmount, residualAmount);

        return new RateAmounts(rateAmounts, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateQ(BigDecimal interestPercent) {
        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    private BigDecimal calculateConstantRateAmounts(
            BigDecimal q,
            BigDecimal amount,
            BigDecimal monthsDuration,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        BigDecimal rateAmount = amount
                .multiply(q.pow(monthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthsDuration.intValue()).subtract(BigDecimal.ONE), 50, RoundingMode.HALF_UP);

        return compareWithResidual(rateAmount, interestAmount, residualAmount);
    }

    private BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateCapitalAmount(
            BigDecimal rateAmounts,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = rateAmounts.subtract(interestAmount);
        if (capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }

    private BigDecimal compareWithResidual(
            BigDecimal rateAmount,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        if (rateAmount.subtract(interestAmount).compareTo(residualAmount) >= 0) {
            return residualAmount.add(interestAmount);
        }
        return rateAmount;
    }
}
