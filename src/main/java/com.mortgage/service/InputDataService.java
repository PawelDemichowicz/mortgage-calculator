package com.mortgage.service;

import com.mortgage.model.InputData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class InputDataService {

    private static final Path INPUT_DATA_FILE_LOCATION = Paths.get("src/main/resources/inputData.csv");

    public InputData read() throws IOException {
        Files.readString(INPUT_DATA_FILE_LOCATION)
                .lines()
                .collect(Collectors.groupingBy(line->line.split(";")[0]));
        return null;
    }
}
