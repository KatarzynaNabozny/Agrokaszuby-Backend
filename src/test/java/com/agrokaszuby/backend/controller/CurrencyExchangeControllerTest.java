package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.Currency;
import com.agrokaszuby.backend.domain.CurrencyExchange;
import com.agrokaszuby.backend.domain.dto.CurrencyExchangeDTO;
import com.agrokaszuby.backend.mapper.CurrencyExchangeMapper;
import com.agrokaszuby.backend.service.CurrencyExchangeDBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(CurrencyExchangeController.class)
class CurrencyExchangeControllerTest {

    public static final String CURRENCY_EXCHANGE_ID = "currencyExchangeId";
    public static final String FROM_CURRENCY = Currency.PLN.name();
    public static final String TO_CURRENCY = Currency.USD.name();
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyExchangeDBService dbService;
    @MockBean
    private CurrencyExchangeMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/currency_exchange";

    @Test
    void shouldFetchEmptyCurrencyExchangeList() throws Exception {
        //Given
        final List<CurrencyExchange> currencyExchanges = new ArrayList<>();
        //When
        when(mapper.mapToCurrencyExchangeDtoList(currencyExchanges)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchCurrencyExchangeList() throws Exception {
        //Given
        List<CurrencyExchangeDTO> currencyExchangeDtoList = new ArrayList<>();
        currencyExchangeDtoList.add(getCurrencyExchangeDTO());
        List<CurrencyExchange> currencyExchanges = new ArrayList<>();
        currencyExchanges.add(getCurrencyExchange());
        //When
        when(mapper.mapToCurrencyExchangeDtoList(currencyExchanges)).thenReturn(currencyExchangeDtoList);
        when(dbService.getAllCurrencyExchanges()).thenReturn(currencyExchanges);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyExchangeId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fromCurrency", Matchers.is(FROM_CURRENCY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].toCurrency", Matchers.is(TO_CURRENCY)));
    }

    private CurrencyExchange getCurrencyExchange() {
        return new CurrencyExchange(1L, Currency.PLN.name(), Currency.USD.name(),
                BigDecimal.ONE, null);
    }

    private CurrencyExchangeDTO getCurrencyExchangeDTO() {
        return CurrencyExchangeDTO.builder()
                .currencyExchangeId(1L)
                .fromCurrency(Currency.PLN.name())
                .toCurrency(Currency.USD.name())
                .rate(BigDecimal.ONE)
                .build();
    }

    @Test
    void testShouldFetchCurrencyExchange() throws Exception {
        //Given & When
        when(mapper.mapToCurrencyExchangeDTO(any(CurrencyExchange.class))).thenReturn(getCurrencyExchangeDTO());
        when(dbService.getCurrencyExchange(anyLong())).thenReturn(getCurrencyExchange());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(CURRENCY_EXCHANGE_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyExchangeId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fromCurrency", Matchers.is(FROM_CURRENCY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toCurrency", Matchers.is(TO_CURRENCY)));
    }

    @Test
    void testShouldFetchCurrencyExchangeByFromCurrency() throws Exception {
        //Given & When
        when(mapper.mapToCurrencyExchangeDTO(any(CurrencyExchange.class))).thenReturn(getCurrencyExchangeDTO());
        when(dbService.getCurrencyExchangeByFromCurrency(anyString())).thenReturn(getCurrencyExchange());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/search/from_currency?fromCurrency=PLN")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(CURRENCY_EXCHANGE_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyExchangeId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fromCurrency", Matchers.is(FROM_CURRENCY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toCurrency", Matchers.is(TO_CURRENCY)));
    }

    @Test
    void testShouldFetchCurrencyExchangeByToCurrency() throws Exception {
        //Given & When
        when(mapper.mapToCurrencyExchangeDTO(any(CurrencyExchange.class))).thenReturn(getCurrencyExchangeDTO());
        when(dbService.getCurrencyExchangeByToCurrency(anyString())).thenReturn(getCurrencyExchange());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/search/to_currency?toCurrency=USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(CURRENCY_EXCHANGE_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyExchangeId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fromCurrency", Matchers.is(FROM_CURRENCY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toCurrency", Matchers.is(TO_CURRENCY)));
    }

    @Test
    void shouldDeleteCurrencyExchange() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(CURRENCY_EXCHANGE_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateCurrencyExchange() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        CurrencyExchangeDTO currencyExchangeDTO = getCurrencyExchangeDTO();
        CurrencyExchange currencyExchange = getCurrencyExchange();
        //When
        when(mapper.mapToCurrencyExchange(any(CurrencyExchangeDTO.class))).thenReturn(currencyExchange);
        when(dbService.saveCurrencyExchange(currencyExchange)).thenReturn(currencyExchange);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currencyExchangeDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveCurrencyExchange(currencyExchange);
    }

}