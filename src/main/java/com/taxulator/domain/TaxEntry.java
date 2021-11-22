package com.taxulator.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TaxEntry {
    private final String name;
    private final BigDecimal income;
    private final BigDecimal revenue;
    private final long orderId;

    public TaxEntry(String name, BigDecimal income, BigDecimal revenue, long orderId) {
        this.name = name;
        this.income = income;
        this.revenue = revenue;
        this.orderId = orderId;
    }
}
