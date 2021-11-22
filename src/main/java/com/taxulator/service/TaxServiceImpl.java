package com.taxulator.service;

import com.taxulator.calculator.TaxCalculator;
import com.taxulator.domain.TaxEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TaxServiceImpl implements TaxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxServiceImpl.class);
    private final List<TaxEntry> taxEntryList = new ArrayList<>();
    private final TaxCalculator taxCalculator;

    @Autowired
    public TaxServiceImpl(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public List<TaxEntry> getLastFiveEntries() {
        int size = taxEntryList.size();
        taxEntryList.sort(Comparator.comparing(TaxEntry::getCreationDate).reversed());
        if (size <= 5) {
            LOGGER.info("Number of entries on the list: " + size);
            return taxEntryList;
        } else return filterLastFive();
    }

    @Override
    public void createTaxEntry(String name, BigDecimal income) {
        BigDecimal revenue = taxCalculator.calcRevenue(income);
        TaxEntry taxEntry = new TaxEntry(name, income, revenue, LocalDateTime.now().withNano(0));
        taxEntryList.add(taxEntry);
        LOGGER.info("Tax entry for company " + name + " has been successfully registered.");
    }

    private List<TaxEntry> filterLastFive() {
        List<TaxEntry> resultList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            resultList.add(taxEntryList.get(i));
        }
        return resultList;
    }
}
