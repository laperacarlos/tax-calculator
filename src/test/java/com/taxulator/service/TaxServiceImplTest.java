package com.taxulator.service;

import com.taxulator.calculator.TaxCalculatorImpl;
import com.taxulator.domain.TaxEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaxServiceImplTest {

    private TaxService taxService;

    @BeforeEach
    void createTaxService() {
        taxService = new TaxServiceImpl(new TaxCalculatorImpl());
    }

    @Test
    void shouldCreateTaxEntry() {
        // given
        TaxEntry taxEntry = new TaxEntry("żółtaFirma", new BigDecimal("2000.00"), null, null);

        //when
        taxService.createTaxEntry(taxEntry);
        List<TaxEntry> entries = taxService.getLastFiveEntries();

        //then
        assertEquals(1, entries.size());
        assertEquals("żółtaFirma", entries.get(0).getName());
        assertEquals(new BigDecimal("2000.00"), entries.get(0).getIncome());
        assertEquals(new BigDecimal("502.05"), entries.get(0).getRevenue());
        assertNotNull(entries.get(0).getId());
    }

    @Test
    void shouldGetSortedLastFiveEntries() {
        // given
        BigDecimal income = new BigDecimal("2000.00");
        TaxEntry taxEntry1 = new TaxEntry("company1", income, null, null);
        TaxEntry taxEntry2 = new TaxEntry("company2", income, null, null);
        TaxEntry taxEntry3 = new TaxEntry("company3", income, null, null);
        TaxEntry taxEntry4 = new TaxEntry("company4", income, null, null);
        TaxEntry taxEntry5 = new TaxEntry("company5", income, null, null);
        TaxEntry taxEntry6 = new TaxEntry("company6", income, null, null);

        //when
        taxService.createTaxEntry(taxEntry1);
        taxService.createTaxEntry(taxEntry2);
        taxService.createTaxEntry(taxEntry3);
        taxService.createTaxEntry(taxEntry4);
        taxService.createTaxEntry(taxEntry5);
        taxService.createTaxEntry(taxEntry6);

        List<TaxEntry> entries = taxService.getLastFiveEntries();

        //then
        assertEquals(5, entries.size());
        assertEquals("company2", entries.get(0).getName());
        assertEquals("company4", entries.get(2).getName());
        assertEquals("company6", entries.get(4).getName());
    }

    @Test
    void shouldGetEmptyList() {
        //when
        List<TaxEntry> entries = taxService.getLastFiveEntries();

        //then
        assertEquals(0, entries.size());
    }
}
