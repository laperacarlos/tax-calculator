package com.taxulator.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TaxEntry {
    private final String name;
    private final BigDecimal income;
    private final BigDecimal revenue;
    private final String id;

    public TaxEntry(String name, BigDecimal income, BigDecimal revenue, String id) {
        this.name = name;
        this.income = income;
        this.revenue = revenue;
        this.id = id;
    }
}
