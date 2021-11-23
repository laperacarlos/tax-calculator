package com.taxulator.mapper;

import com.taxulator.domain.TaxEntry;
import com.taxulator.domain.TaxEntryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        TaxEntry entry1 = new TaxEntry("comp1", income, revenue, "id1");
        TaxEntry entry2 = new TaxEntry("comp2", income, revenue, "id2");
        entryList.add(entry1);
        entryList.add(entry2);

        //when
        List<TaxEntryDto> taxEntryDtoList = mapper.mapToTaxEntryDtoList(entryList);

        //then
        assertEquals(2, taxEntryDtoList.size());
        assertEquals("id1", taxEntryDtoList.get(0).getId());
        assertEquals("comp1", taxEntryDtoList.get(0).getName());
        assertEquals(new BigDecimal("2000.00"), taxEntryDtoList.get(0).getIncome());
        assertEquals(new BigDecimal("502.05"), taxEntryDtoList.get(0).getRevenue());
        assertEquals("id2", taxEntryDtoList.get(1).getId());
        assertEquals("comp2", taxEntryDtoList.get(1).getName());
    }

    @Test
    void shouldMapToTaxEntry() {
        //given
        TaxEntryDto taxEntryDto = new TaxEntryDto("żółtaFirma", new BigDecimal("2000.00"), null, null);

        //when
        TaxEntry taxEntry = mapper.mapToTaxEntry(taxEntryDto);

        //then
        assertEquals("żółtaFirma", taxEntry.getName());
        assertEquals(new BigDecimal("2000.00"), taxEntry.getIncome());
        assertNull(taxEntry.getRevenue());
        assertNull(taxEntry.getId());
    }
}
