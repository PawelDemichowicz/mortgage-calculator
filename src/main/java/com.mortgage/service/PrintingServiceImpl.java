package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.Rate;
import com.mortgage.model.Summary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {

    private static final String SEPARATOR = createSeparator();

    private static final Path RESULT_FILE_PATH = Paths.get("src/main/resources/result.csv");

    public PrintingServiceImpl() {
        clearFileAtStartup();
    }

    @Override
    public void printInputDataInfo(InputData inputData) {
        String mortgageInformation = MORTGAGE_INFORMATION.formatted(
                inputData.getAmount(),
                inputData.getMonthsDuration(),
                inputData.getInterestDisplay()
        );

        if (Optional.ofNullable(inputData.getOverpaymentSchema())
                .map(schema -> !schema.isEmpty())
                .orElse(false)
        ) {
            String overpaymentMessage = OVERPAYMENT_INFORMATION.formatted(
                    Overpayment.REDUCE_PERIOD.equals(inputData.getOverpaymentReduceWay())
                            ? OVERPAYMENT_REDUCE_PERIOD
                            : OVERPAYMENT_REDUCE_RATE,
                    inputData.getOverpaymentSchema()
            );
            mortgageInformation += overpaymentMessage;
        }
        printMessage(mortgageInformation);
    }

    @Override
    public void printRates(List<Rate> rates) {
        rates.forEach(rate -> {
            printMessage(formatRateLine(rate));
            if (TimePointServiceImpl.YEAR.equals(rate.getTimePoint().getMonth())) {
                printMessage(SEPARATOR);
            }
        });
        printMessage(System.lineSeparator());
    }

    @Override
    public void printSummary(Summary summary) {
        printMessage(SUMMARY_INFORMATION.formatted(
                summary.getInterestSum(),
                summary.getOverpaymentProvisionSum(),
                summary.getTotalLost()
        ));
    }

    private String formatRateLine(Rate rate) {
        return String.format(SCHEDULE_TABLE_FORMAT,
                RATE_LINE_KEYS.get(0), rate.getRateNumber(),
                RATE_LINE_KEYS.get(1), rate.getTimePoint().getDate(),
                RATE_LINE_KEYS.get(2), rate.getTimePoint().getYear(),
                RATE_LINE_KEYS.get(3), rate.getTimePoint().getMonth(),
                RATE_LINE_KEYS.get(4), rate.getRateAmounts().getRateAmount(),
                RATE_LINE_KEYS.get(5), rate.getRateAmounts().getInterestAmount(),
                RATE_LINE_KEYS.get(6), rate.getRateAmounts().getCapitalAmount(),
                RATE_LINE_KEYS.get(7), rate.getRateAmounts().getOverpayment().getAmount(),
                RATE_LINE_KEYS.get(8), rate.getMortgageResidual().getAmount(),
                RATE_LINE_KEYS.get(9), rate.getMortgageResidual().getDuration()
        );
    }

    private static String createSeparator() {
        return String.valueOf('-').repeat(Math.max(0, 200)) + System.lineSeparator();
    }

    private void printMessage(String message) {
        try {
            Files.writeString(RESULT_FILE_PATH, message, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing data to file" + e.getMessage());
        }
    }

    private void clearFileAtStartup() {
        try {
            Files.writeString(RESULT_FILE_PATH, "", StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("Error clearing file at startup: " + e.getMessage());
        }
    }
}
