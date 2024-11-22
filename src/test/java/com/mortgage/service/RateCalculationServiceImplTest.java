package com.mortgage.service;

import com.mortgage.fixtures.TestData;
import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;
import com.mortgage.model.Rate;
import com.mortgage.model.RateAmounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RateCalculationServiceImplTest {

    @InjectMocks
    RateCalculationServiceImpl rateCalculationService;

    @Mock
    private TimePointService timePointService;

    @Mock
    private AmountsCalculationService amountsCalculationService;

    @Mock
    private OverpaymentCalculateService overpaymentCalculateService;

    @Mock
    private ResidualCalculationService residualCalculationService;

    @Mock
    private ReferenceCalculationService referenceCalculationService;

    @Test
    @DisplayName("Calculate rates with mocks")
    void shouldCalculateRatesCorrectlyWithMocks() {
        // Given
        InputData inputData = TestData.someInputData();
        List<Rate> expected = IntStream.rangeClosed(1, 180).boxed()
                .map(this::createRate)
                .toList();

        Mockito.when(timePointService.calculate(any(BigDecimal.class), any(InputData.class)))
                .thenReturn(TestData.someTimePoint());

        Mockito.when(amountsCalculationService.calculate(any(InputData.class), any(Overpayment.class)))
                .thenReturn(TestData.someRateAmounts());
        Mockito.when(amountsCalculationService.calculate(any(InputData.class), any(Overpayment.class), any(Rate.class)))
                .thenReturn(TestData.someRateAmounts());

        Mockito.when(overpaymentCalculateService.calculation(any(BigDecimal.class), any(InputData.class)))
                .thenReturn(TestData.someOverpayment());

        Mockito.when(residualCalculationService.calculate(any(RateAmounts.class), any(InputData.class)))
                .thenReturn(TestData.someMortgageResidual());
        Mockito.when(residualCalculationService.calculate(any(RateAmounts.class), any(Rate.class)))
                .thenReturn(TestData.someMortgageResidual());

        Mockito.when(referenceCalculationService.calculate(any(InputData.class)))
                .thenReturn(TestData.someMortgageReference());
        Mockito.when(referenceCalculationService.calculate(any(InputData.class), any(RateAmounts.class), any(Rate.class)))
                .thenReturn(TestData.someMortgageReference());

        // When
        List<Rate> result = rateCalculationService.calculate(inputData);

        // Then
        Assertions.assertEquals(expected, result);
    }

    private Rate createRate(final Integer index) {
        return TestData.someRate()
                .withRateNumber(BigDecimal.valueOf(index))
                .withRateAmounts(TestData.someRateAmounts());
    }
}