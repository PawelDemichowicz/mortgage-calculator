package com.mortgage.configuration;

import com.mortgage.MortgageCalculator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MortgageCalculator.class)
public class ApplicationConfiguration {
}
