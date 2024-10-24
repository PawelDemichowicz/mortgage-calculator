package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class OverpaymentCalculateServiceImpl implements OverpaymentCalculateService {
    @Override
    public Overpayment calculation(BigDecimal rateNumber, InputData inputData) {
        BigDecimal overpaymentAmount = calculateAmount(rateNumber, inputData.getOverpaymentSchema())
                .orElse(BigDecimal.ZERO);
        BigDecimal overpaymentProvision = calculateProvision(rateNumber, overpaymentAmount, inputData);

        return new Overpayment(overpaymentAmount, overpaymentProvision);
    }

    private Optional<BigDecimal> calculateAmount(BigDecimal rateNumber, Map<Integer, BigDecimal> overpaymentSchema) {
        for (Map.Entry<Integer, BigDecimal> entry : overpaymentSchema.entrySet()) {
            if (rateNumber.equals(BigDecimal.valueOf(entry.getKey()))) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    private BigDecimal calculateProvision(BigDecimal rateNumber, BigDecimal overpaymentAmount, InputData inputData) {
        if (BigDecimal.ZERO.equals(overpaymentAmount)) {
            return BigDecimal.ZERO;
        }

        if (rateNumber.compareTo(inputData.getOverpaymentProvisionMonths()) > 0) {
            return BigDecimal.ZERO;
        }

        return overpaymentAmount.multiply(inputData.getOverpaymentProvisionPercent());
    }
}
