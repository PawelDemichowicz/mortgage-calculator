import model.InputData;
import model.MortgageReference;
import service.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("298000"))
                .withMonthsDuration(new BigDecimal(360));

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(),
                        new DecreasingAmountsCalculationServiceImpl()
                ),
                new OverpaymentCalculateServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );
        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(inputData);
    }
}