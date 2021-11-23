package com.taxulator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TaxEntryDto {
    private final String name;
    private final BigDecimal income;
    private final BigDecimal revenue;
    private final String id;
}
