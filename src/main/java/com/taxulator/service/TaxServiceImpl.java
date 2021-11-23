package com.taxulator.service;

import com.taxulator.calculator.TaxCalculator;
import com.taxulator.domain.TaxEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaxServiceImpl implements TaxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxServiceImpl.class);
    private static final Integer MAX_SIZE = 5;
    private final List<TaxEntry> taxEntryList = new ArrayList<>();
    private final TaxCalculator taxCalculator;

    @Autowired
    public TaxServiceImpl(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public List<TaxEntry> getLastFiveEntries() {
        int size = taxEntryList.size();
        LOGGER.info("Number of entries on the list: " + size);
        return taxEntryList;
    }

    @Override
    public void createTaxEntry(TaxEntry taxEntry) {
        TaxEntry entry = calculateTaxEntry(taxEntry);
        addToList(entry);
        LOGGER.info("Tax entry for company " + entry.getName() + " has been successfully registered.");
    }

    private synchronized void addToList(TaxEntry taxEntry) {
        if (taxEntryList.size() == MAX_SIZE) {
            taxEntryList.remove(0);
        }
        taxEntryList.add(taxEntry);
    }

    private TaxEntry calculateTaxEntry(TaxEntry taxEntry) {
        String name = taxEntry.getName();
        BigDecimal income = taxEntry.getIncome();
        BigDecimal revenue = taxCalculator.calcRevenue(income);
        String id = UUID.randomUUID().toString();
        return new TaxEntry(name, income, revenue, id);
    }
}

