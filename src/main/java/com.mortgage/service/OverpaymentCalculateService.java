package com.mortgage.service;

import com.mortgage.model.InputData;
import com.mortgage.model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculateService {
    Overpayment calculation(BigDecimal rateNumber, InputData inputData);
}
