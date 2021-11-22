package com.taxulator.mapper;

import com.taxulator.domain.TaxEntry;
import com.taxulator.domain.TaxEntryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxEntryMapper {

    public List<TaxEntryDto> mapToTaxEntryDtoList(final List<TaxEntry> entryList) {
        return entryList.stream()
                .map(this::mapToTaxEntryDto)
                .collect(Collectors.toList());
    }

    private TaxEntryDto mapToTaxEntryDto(final TaxEntry taxEntry) {
        return new TaxEntryDto(
                taxEntry.getName(),
                taxEntry.getIncome(),
                taxEntry.getRevenue(),
                taxEntry.getOrderId()
        );
    }
}
