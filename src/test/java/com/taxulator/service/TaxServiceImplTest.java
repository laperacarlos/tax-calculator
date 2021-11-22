package com.taxulator.service;

import com.taxulator.calculator.TaxCalculatorImpl;
import com.taxulator.domain.TaxEntry;
import com.taxulator.utility.SequenceGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxServiceImplTest {

    private TaxService taxService;

    @BeforeEach
    void createTaxService() {
        taxService = new TaxServiceImpl(new TaxCalculatorImpl(), new SequenceGeneratorImpl());
    }

    @Test
    void shouldCreateTaxEntry() {
        // given
        BigDecimal income = new BigDecimal("2000.00");
        String name = "żółtaFirma";

        //when
        taxService.createTaxEntry(name, income);
        List<TaxEntry> entries = taxService.getLastFiveEntries();

        //then
        assertEquals(1, entries.size());
        assertEquals("żółtaFirma", entries.get(0).getName());
        assertEquals(new BigDecimal("2000.00"), entries.get(0).getIncome());
        assertEquals(new BigDecimal("502.05"), entries.get(0).getRevenue());
        assertEquals(1L, entries.get(0).getOrderId());
    }

    @Test
    void shouldGetSortedLastFiveEntries() {
        // given
        BigDecimal income = new BigDecimal("2000.00");

        //when
        taxService.createTaxEntry("company1", income);
        taxService.createTaxEntry("company2", income);
        taxService.createTaxEntry("company3", income);
        taxService.createTaxEntry("company4", income);
        taxService.createTaxEntry("company5", income);
        taxService.createTaxEntry("company6", income);

        List<TaxEntry> entries = taxService.getLastFiveEntries();

        //then
        assertEquals(5, entries.size());
        assertEquals("company6", entries.get(0).getName());
        assertEquals("company4", entries.get(2).getName());
        assertEquals("company2", entries.get(4).getName());
    }

    @Test
    void shouldGetEmptyList() {
        //when
        List<TaxEntry> entries = taxService.getLastFiveEntries();

        //then
        assertEquals(0, entries.size());
    }
}
