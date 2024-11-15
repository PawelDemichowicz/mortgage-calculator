package com.mortgage.service;

import com.mortgage.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateCalculationServiceImpl implements RateCalculationService {
    private final TimePointService timePointService;
    private final AmountsCalculationService amountsCalculationService;
    private final OverpaymentCalculateService overpaymentCalculateService;
    private final ResidualCalculationService residualCalculationService;
    private final ReferenceCalculationService referenceCalculationService;

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;
        Rate firstRate = calculateRate(rateNumber, inputData);
        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (
                BigDecimal index = rateNumber.add(BigDecimal.ONE);
                index.compareTo(inputData.getMonthsDuration()) <= 0;
                index = index.add(BigDecimal.ONE)
        ) {
            Rate nextRate = calculateRate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;

            if (mortgageFinished(nextRate)) {
                break;
            }
        }

        return rates;
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculateService.calculation(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculateService.calculation(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference =
                referenceCalculationService.calculate(inputData, rateAmounts, previousRate);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private static boolean mortgageFinished(Rate nextRate) {
        BigDecimal toCompare = nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP);
        return BigDecimal.ZERO.equals(toCompare);
    }

}
