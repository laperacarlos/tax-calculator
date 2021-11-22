package com.taxulator.mapper;

import com.taxulator.domain.TaxEntry;
import com.taxulator.domain.TaxEntryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxEntryMapperTest {

    private TaxEntryMapper mapper;

    @BeforeEach
    void setMapper() {
        mapper = new TaxEntryMapper();
    }

    @Test
    void shouldMapToTaxEntryDtoList() {
        //given
        List<TaxEntry> entryList = new ArrayList<>();
        BigDecimal income = new BigDecimal("2000.00");
        BigDecimal revenue = new BigDecimal("502.05");
        TaxEntry entry1 = new TaxEntry("comp1", income, revenue, 1L);
        TaxEntry entry2 = new TaxEntry("comp2", income, revenue, 2L);
        entryList.add(entry1);
        entryList.add(entry2);

        //when
        List<TaxEntryDto> taxEntryDtoList = mapper.mapToTaxEntryDtoList(entryList);

        //then
        assertEquals(2, taxEntryDtoList.size());
        assertEquals(1L, taxEntryDtoList.get(0).getOrderId());
        assertEquals("comp1", taxEntryDtoList.get(0).getName());
        assertEquals(new BigDecimal("2000.00"), taxEntryDtoList.get(0).getIncome());
        assertEquals(new BigDecimal("502.05"), taxEntryDtoList.get(0).getRevenue());
        assertEquals(2L, taxEntryDtoList.get(1).getOrderId());
        assertEquals("comp2", taxEntryDtoList.get(1).getName());
    }
}
