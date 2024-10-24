package com.mortgage.service;

import com.mortgage.model.Rate;
import com.mortgage.model.Summary;

import java.util.List;

public interface SummaryService {
    Summary calculate(List<Rate> rates);
}
