package com.mortgage.integration;

import com.mortgage.configuration.ApplicationConfiguration;
import com.mortgage.service.MortgageCalculationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
public class MortgageCalculationServiceIT {

    private static final Path RESULT_FILE = Paths.get("src/main/result.csv");
    private static final String EXPECTED_GENERATED_FILE = "classpath:expectedGeneratedResult.txt";

    @Autowired
    private MortgageCalculationServiceImpl mortgageCalculationService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(mortgageCalculationService);
    }

    @Test
    @DisplayName("Whole application calculation works correctly")
    void test() {
        // Given, when
        mortgageCalculationService.calculate();

        // Then
        final List<String> resultContent = readResultContent();
        final List<String> expectedContent = readExpectedContent();

        for (int i = 0; i < expectedContent.size(); i++) {
            Assertions.assertEquals(expectedContent.get(i), resultContent.get(i));
        }
    }

    private List<String> readResultContent() {
        try {
            return Files.readAllLines(RESULT_FILE);
        } catch (Exception e) {
            Assertions.fail("Reading file failed", e);
        }
        return List.of();
    }

    private List<String> readExpectedContent() {
        try {
            File file = ResourceUtils.getFile(EXPECTED_GENERATED_FILE);
            return Files.readAllLines(file.toPath());
        } catch (Exception e) {
            Assertions.fail("Reading file failed", e);
        }
        return List.of();
    }
}
