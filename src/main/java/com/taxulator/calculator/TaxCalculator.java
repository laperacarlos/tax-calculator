package com.taxulator.calculator;

import java.math.BigDecimal;

public interface TaxCalculator {
    BigDecimal calcRevenue(BigDecimal income);
}
