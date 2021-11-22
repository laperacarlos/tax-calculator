package com.taxulator.service;

import com.taxulator.calculator.TaxCalculator;
import com.taxulator.domain.TaxEntry;
import com.taxulator.utility.SequenceGeneratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TaxServiceImpl implements TaxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxServiceImpl.class);
    private final List<TaxEntry> taxEntryList = new ArrayList<>();
    private final TaxCalculator taxCalculator;
    private final SequenceGeneratorImpl sequenceGenerator;

    @Autowired
    public TaxServiceImpl(TaxCalculator taxCalculator, SequenceGeneratorImpl sequenceGenerator) {
        this.taxCalculator = taxCalculator;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<TaxEntry> getLastFiveEntries() {
        int size = taxEntryList.size();
        sortTaxEntryList();
        if (size <= 5) {
            LOGGER.info("Number of entries on the list: " + size);
            return taxEntryList;
        } else return filterLastFive();
    }

    @Override
    public void createTaxEntry(String name, BigDecimal income) {
        BigDecimal revenue = taxCalculator.calcRevenue(income);
        TaxEntry taxEntry = new TaxEntry(name, income, revenue, sequenceGenerator.getNext());
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

    private void sortTaxEntryList() {
        taxEntryList.sort(Comparator.comparing(TaxEntry::getOrderId).reversed());
    }
}

