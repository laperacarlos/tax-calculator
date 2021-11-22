package com.taxulator.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TaxCalculatorImpl implements TaxCalculator {
    private static final BigDecimal ZUS = new BigDecimal("1380.18");
    private static final BigDecimal PIT_RATE = new BigDecimal("0.81");
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxCalculatorImpl.class);

    @Override
    public BigDecimal calcRevenue(BigDecimal income) {
        BigDecimal afterZUS = deductZUS(income);
        return deductPIT(afterZUS);
    }

    private BigDecimal deductZUS(BigDecimal income) {
        if (income.compareTo(ZUS) >= 0) {
            return income.subtract(ZUS);
        } else {
            BigDecimal payExtra = income.subtract(ZUS).abs();
            LOGGER.info("Congratulations, you are not earning enough to pay ZUS! Ho have to pay extra amount of: " + payExtra + " PLN. Long live ZUS!");
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal deductPIT(BigDecimal taxBase) {
        BigDecimal result = taxBase.multiply(PIT_RATE);
        result = result.setScale(2, RoundingMode.HALF_EVEN);
        return result;
    }
}
