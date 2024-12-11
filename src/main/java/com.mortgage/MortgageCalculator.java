package com.mortgage;

import com.mortgage.configuration.ApplicationConfiguration;
import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.RateType;
import com.mortgage.service.InputDataService;
import com.mortgage.service.MortgageCalculationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MortgageCalculator {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        InputData inputData;

//        try {
//            inputData = new InputDataService().read();
//        } catch (Exception e) {
//            System.err.println("Error while loading input data, interrupting. Error: " + e.getMessage());
//            return;
//        }
//
//        inputData = inputData.withAmount(new BigDecimal("298000"))
//                .withMonthsDuration(new BigDecimal(360))
//                .withRateType(RateType.CONSTANT)
//                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);

        MortgageCalculationService mortgageCalculationService = context.getBean(MortgageCalculationService.class);
        mortgageCalculationService.calculate();
    }
}