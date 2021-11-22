package com.taxulator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TaxEntryDto {
    private final String name;
    private final BigDecimal income;
    private final BigDecimal revenue;
    @JsonIgnore
    private final long orderId;
}
