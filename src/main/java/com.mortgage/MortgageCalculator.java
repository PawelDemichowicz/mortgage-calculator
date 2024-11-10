package com.mortgage;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.RateType;
import com.mortgage.service.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class MortgageCalculator {
    public static void main(String[] args) {
        InputData inputData;
        try {
            inputData = new InputDataService().read();
        } catch (Exception e) {
            System.err.println("Error while loading input data, interrupting. Error: " + e.getMessage());
            return;
        }
//        //Todo: Provide default input data from the file
//        InputData inputData = InputData.defaultInputData()
//                .withAmount(new BigDecimal("298000"))
//                .withMonthsDuration(new BigDecimal(360))
//                .withRateType(RateType.CONSTANT)
//                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);
//        CalculatorCreator.getInstance().calculate(inputData);
    }

    static class CalculatorCreator {
        private static MortgageCalculationService instance;

        private CalculatorCreator() {
        }

        public static MortgageCalculationService getInstance() {
            if (Objects.isNull(instance)) {
                instance = new MortgageCalculationServiceImpl(
                        new PrintingServiceImpl(),
                        new RateCalculationServiceImpl(
                                new TimePointServiceImpl(),
                                new AmountsCalculationServiceImpl(
                                        new ConstantAmountsCalculationServiceImpl(),
                                        new DecreasingAmountsCalculationServiceImpl()
                                ),
                                new OverpaymentCalculateServiceImpl(),
                                new ResidualCalculationServiceImpl(),
                                new ReferenceCalculationServiceImpl()),
                        SummaryServiceFactory.create()
                );
            }
            return instance;
        }
    }
}