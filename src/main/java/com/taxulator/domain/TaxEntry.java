package com.taxulator.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class TaxEntry {
    private final String name;
    private final BigDecimal income;
    private final BigDecimal revenue;
    private final LocalDateTime creationDate;

    public TaxEntry(String name, BigDecimal income, BigDecimal revenue, LocalDateTime creationDate) {
        this.name = name;
        this.income = income;
        this.revenue = revenue;
        this.creationDate = creationDate;
    }
}
