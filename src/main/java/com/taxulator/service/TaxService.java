package com.taxulator.service;

import com.taxulator.domain.TaxEntry;

import java.math.BigDecimal;
import java.util.List;

public interface TaxService {
    List<TaxEntry> getLastFiveEntries();

    void createTaxEntry(String name, BigDecimal income);
}
