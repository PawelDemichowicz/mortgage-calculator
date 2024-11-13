package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.Rate;
import com.mortgage.model.Summary;

import java.util.List;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {

    private static final String SEPARATOR = createSeparator('-', 200);

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

    private static String createSeparator(char sign, int length) {
        return String.valueOf(sign).repeat(Math.max(0, length)) + System.lineSeparator();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
