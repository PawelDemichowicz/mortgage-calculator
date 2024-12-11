package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Rate;
import com.mortgage.model.Summary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MortgageCalculationServiceImpl implements MortgageCalculationService {

    private final PrintingService printingService;
    private final RateCalculationService rateCalculationService;
    private final SummaryService summaryService;
    private final InputDataService inputDataService;

    @Override
    public void calculate() {
        final Optional<InputData> inputData = inputDataService.read();
        if (inputData.isEmpty()) {
            return;
        }

        printingService.printInputDataInfo(inputData.get());
        List<Rate> rates = rateCalculationService.calculate(inputData.get());

        Summary summary = summaryService.calculate(rates);
        printingService.printSummary(summary);

        printingService.printRates(rates);
    }
}
