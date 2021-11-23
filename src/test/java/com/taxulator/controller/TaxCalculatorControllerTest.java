package com.taxulator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxulator.domain.TaxEntry;
import com.taxulator.domain.TaxEntryDto;
import com.taxulator.mapper.TaxEntryMapper;
import com.taxulator.service.TaxServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaxCalculatorController.class)
public class TaxCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxServiceImpl taxService;

    @MockBean
    private TaxEntryMapper mapper;

    @Test
    void shouldCreateTaxEntry() throws Exception {
        //given
        TaxEntryDto taxEntryDto = new TaxEntryDto("żółtaFirma", new BigDecimal("2000.00"), null, null);
        TaxEntry taxEntry = new TaxEntry("żółtaFirma", new BigDecimal("2000.00"), new BigDecimal("502.05"), "ssahh-ffh44");

        when(mapper.mapToTaxEntry(taxEntryDto)).thenReturn(taxEntry);
        doNothing().when(taxService).createTaxEntry(taxEntry);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(taxEntryDto);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldGetLastFiveCalculations() throws Exception {
        //given
        List<TaxEntryDto> taxEntryDtoList = List.of(new TaxEntryDto("żółtaFirma", new BigDecimal("2000.00"), new BigDecimal("502.05"), "ssahh-ffh44"));
        List<TaxEntry> taxEntryList = List.of(new TaxEntry("żółtaFirma", new BigDecimal("2000.00"), new BigDecimal("502.05"), "ssahh-ffh44"));

        when(mapper.mapToTaxEntryDtoList(taxEntryList)).thenReturn(taxEntryDtoList);
        when(taxService.getLastFiveEntries()).thenReturn(taxEntryList);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/entries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("żółtaFirma")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].income", Matchers.is(2000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].revenue", Matchers.is(502.05)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("ssahh-ffh44")));
    }
}
