package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Rate;
import com.mortgage.model.Summary;

import java.util.List;

public interface PrintingService {

    String MORTGAGE_INFORMATION = """
            KWOTA KREDYTU: %s ZL
            OKRES KREDYTOWANIA: %s MIESIECY
            ODSETKI: %s %%
            """;

    String OVERPAYMENT_INFORMATION = """
            %s
            SCHEMAT DOKONYWANIA NADPLAT: %s
            """;

    String SUMMARY_INFORMATION = """
            
            SUMA ODSETEK: %s ZL
            PROWIZJA ZA NADPLATY: %s ZL
            SUMA STRAT: %s ZL
            
            """;

    String SCHEDULE_TABLE_FORMAT =
            "%4s %3s  |   " +
                    "%4s %3s  |   " +
                    "%3s %2s  |   " +
                    "%4s %2s  |   " +
                    "%4s %8s  |   " +
                    "%7s %8s  |   " +
                    "%7s %8s  |   " +
                    "%7s %8s  |   " +
                    "%7s %8s  |   " +
                    "%7s %3s%n";

    List<String> RATE_LINE_KEYS = List.of(
            "NR: ",
            "DATA: ",
            "ROK: ",
            "MIESIAC: ",
            "RATA: ",
            "ODSETKI:",
            "KAPITAL:",
            "NADPLATA:",
            "KAPITAL: ",
            "PKWOTA: ",
            "PMSC: "
    );

    String OVERPAYMENT_REDUCE_RATE = "NADPLATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD = "NADPLATA, SKROCENIE OKRESU";

    void printInputDataInfo(final InputData inputData);

    void printRates(List<Rate> rates);

    void printSummary(Summary summary);
}
