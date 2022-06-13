package com.agrokaszuby.backend.controller;


import com.agrokaszuby.backend.domain.CurrencyExchange;
import com.agrokaszuby.backend.domain.dto.CurrencyExchangeDTO;
import com.agrokaszuby.backend.exception.CurrencyExchangeNotFoundException;
import com.agrokaszuby.backend.mapper.CurrencyExchangeMapper;
import com.agrokaszuby.backend.service.CurrencyExchangeDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend/currency_exchange")
public class CurrencyExchangeController {

    private final CurrencyExchangeDBService service;
    private final CurrencyExchangeMapper mapper;

    @GetMapping
    public ResponseEntity<List<CurrencyExchangeDTO>> getCurrencyExchanges() {
        List<CurrencyExchange> currencyExchanges = service.getAllCurrencyExchanges();
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDtoList(currencyExchanges));
    }

    @GetMapping(value = "/{currencyExchangeId}")
    public ResponseEntity<CurrencyExchangeDTO> getCurrencyExchange(@PathVariable Long currencyExchangeId) throws CurrencyExchangeNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDTO(
                service.getCurrencyExchange(currencyExchangeId)));
    }

    @GetMapping(value = "/search/from_currency")
    public ResponseEntity<CurrencyExchangeDTO> getCurrencyExchangeByFromCurrency(@RequestParam(name = "fromCurrency") String fromCurrency)
            throws CurrencyExchangeNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDTO(
                service.getCurrencyExchangeByFromCurrency(fromCurrency)));
    }

    @GetMapping(value = "/search/to_currency")
    public ResponseEntity<CurrencyExchangeDTO> getCurrencyExchangeByToCurrency(@RequestParam(name = "toCurrency") String toCurrency)
            throws CurrencyExchangeNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDTO(
                service.getCurrencyExchangeByToCurrency(toCurrency)));
    }

    @GetMapping(value = "/search/date")
    public ResponseEntity<CurrencyExchangeDTO> getCurrencyExchangeByDate(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) throws CurrencyExchangeNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDTO(
                service.getCurrencyExchangeByDate(date)));
    }

    @GetMapping(value = "/search/from_to_currency_date")
    public ResponseEntity<CurrencyExchangeDTO> getCurrencyExchangeByFromCurrencyAndToCurrencyAndDate(
            @RequestParam(name = "fromCurrency") String fromCurrency,
            @RequestParam(name = "toCurrency") String toCurrency,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
            throws CurrencyExchangeNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDTO(
                service.getCurrencyExchangeByFromCurrencyAndToCurrencyAndDate(fromCurrency, toCurrency, date)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCurrencyExchange(@RequestBody CurrencyExchangeDTO currencyExchangeDTO) {
        CurrencyExchange currencyExchange = mapper.mapToCurrencyExchange(currencyExchangeDTO);
        service.saveCurrencyExchange(currencyExchange);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CurrencyExchangeDTO> updateCurrencyExchange(@RequestBody CurrencyExchangeDTO currencyExchangeDto) {
        CurrencyExchange currencyExchange = mapper.mapToCurrencyExchange(currencyExchangeDto);
        CurrencyExchange savedCurrencyExchange = service.saveCurrencyExchange(currencyExchange);
        return ResponseEntity.ok(mapper.mapToCurrencyExchangeDTO(savedCurrencyExchange));
    }

    @DeleteMapping(value = "/{currencyExchangeId}")
    public ResponseEntity<Void> deleteCurrencyExchange(@PathVariable Long currencyExchangeId) throws CurrencyExchangeNotFoundException {
        service.deleteCurrencyExchange(currencyExchangeId);
        return ResponseEntity.ok().build();
    }

}
