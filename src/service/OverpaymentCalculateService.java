package service;

import model.InputData;
import model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculateService {
    Overpayment calculation(BigDecimal rateNumber, InputData inputData);
}
