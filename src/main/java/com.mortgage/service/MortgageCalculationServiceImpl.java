package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Rate;
import com.mortgage.model.Summary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MortgageCalculationServiceImpl implements MortgageCalculationService {

    private final PrintingService printingService;
    private final RateCalculationService rateCalculationService;
    private final SummaryService summaryService;

    @Override
    public void calculate(InputData inputData) {
        printingService.printInputDataInfo(inputData);
        List<Rate> rates = rateCalculationService.calculate(inputData);

        Summary summary = summaryService.calculate(rates);
        printingService.printSummary(summary);

        printingService.printRates(rates);
    }
}
