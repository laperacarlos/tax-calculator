package com.taxulator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorImplTest {

    private TaxCalculator taxCalculator;

    @BeforeEach
    void createCalculator() {
        taxCalculator = new TaxCalculatorImpl();
    }

    @Test
    void shouldReturnZero() {
        //given
        BigDecimal income = new BigDecimal("1000.00");
        BigDecimal minusIncome = new BigDecimal("-50.00");

        //when
        BigDecimal netRevenue = taxCalculator.calcRevenue(income);
        BigDecimal netRevenueWithMinusIncome = taxCalculator.calcRevenue(minusIncome);

        //then
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN), netRevenue);
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN), netRevenueWithMinusIncome);
    }

    @Test
    void shouldCalculateNetRevenue() {
        //given
        BigDecimal income = new BigDecimal("2000.00");

        //when
        BigDecimal netRevenue = taxCalculator.calcRevenue(income);

        //then
        assertEquals(new BigDecimal("502.05"), netRevenue);
    }
}
