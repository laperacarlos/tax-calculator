package com.taxulator.controller;

import com.taxulator.domain.TaxEntryDto;
import com.taxulator.mapper.TaxEntryMapper;
import com.taxulator.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaxCalculatorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxCalculatorController.class);
    private final TaxService taxService;
    private final TaxEntryMapper mapper;

    @GetMapping(value = "entries")
    public List<TaxEntryDto> getLastFiveEntries() {
        List<TaxEntryDto> resultList = mapper.mapToTaxEntryDtoList(taxService.getLastFiveEntries());
        for (TaxEntryDto entry : resultList) {
            LOGGER.info("Tax entry for company {}: income: {} PLN, net revenue: {} PLN.", entry.getName(), entry.getIncome(), entry.getRevenue());
        }
        return resultList;
    }

    @PostMapping(value = "entries", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTaxEntry(@RequestBody TaxEntryDto taxEntryDto) {
        taxService.createTaxEntry(taxEntryDto.getName(), taxEntryDto.getIncome());
    }
}
