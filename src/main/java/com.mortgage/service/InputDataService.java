package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.RateType;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputDataService {

    private static final Path INPUT_DATA_FILE_LOCATION = Paths.get("src/main/resources/inputData.csv");

    public InputData read() throws IOException {
        Map<String, List<String>> content = Files.readString(INPUT_DATA_FILE_LOCATION)
                .lines()
                .collect(Collectors.groupingBy(line -> line.split(";")[0]));
        validate(content);

        Map<String, String> inputData = content.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0).split(";")[1]));

        return InputData.builder()
                .repaymentStartDate(
                        Optional.ofNullable(inputData.get("repaymentStartDate"))
                                .map(LocalDate::parse)
                                .orElseThrow())
                .wiborPercent(
                        Optional.ofNullable(inputData.get("wiborPercent"))
                                .map(BigDecimal::new)
                                .orElseThrow())
                .amount(
                        Optional.ofNullable(inputData.get("amount"))
                                .map(BigDecimal::new)
                                .orElseThrow())
                .monthsDuration(
                        Optional.ofNullable(inputData.get("monthsDuration"))
                                .map(BigDecimal::new)
                                .orElseThrow())
                .rateType(
                        Optional.ofNullable(inputData.get("rateType"))
                                .map(RateType::valueOf)
                                .orElseThrow())
                .bankMarginPercent(
                        Optional.ofNullable(inputData.get("bankMarginPercent"))
                                .map(BigDecimal::new)
                                .orElseThrow())
                .overpaymentSchema(
                        Optional.ofNullable(inputData.get("overpaymentSchema"))
                                .map(this::calculateSchema)
                                .orElseThrow())
                .overpaymentReduceWay(
                        Optional.ofNullable(inputData.get("overpaymentReduceWay"))
                                .orElseThrow())
                .overpaymentProvisionPercent(
                        Optional.ofNullable(inputData.get("overpaymentProvisionPercent"))
                                .map(BigDecimal::new)
                                .orElseThrow())
                .overpaymentProvisionMonths(
                        Optional.ofNullable(inputData.get("overpaymentProvisionMonths"))
                                .map(BigDecimal::new)
                                .orElseThrow())
                .build();
    }

    private Map<Integer, BigDecimal> calculateSchema(String schema) {
        return Stream.of(schema.split(","))
                .map(entry -> Map.entry(entry.split(":")[0], entry.split(":")[1]))
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.getKey()),
                        entry -> new BigDecimal(entry.getValue()),
                        (v1, v2) -> v2,
                        TreeMap::new
                ));
    }

    private static void validate(final Map<String, List<String>> content) {
        if (content.values().stream().anyMatch(values -> values.size() != 1)) {
            throw new IllegalArgumentException("Configuration mismatch");
        }
    }
}
