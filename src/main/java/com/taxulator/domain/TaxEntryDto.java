package com.taxulator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TaxEntryDto {
    private final String name;
    private final BigDecimal income;
    private final BigDecimal revenue;
    private final LocalDateTime creationDate;
}
