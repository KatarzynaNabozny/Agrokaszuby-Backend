package com.agrokaszuby.backend.controller;


import com.agrokaszuby.backend.domain.CurrencyExchange;
import com.agrokaszuby.backend.domain.dto.CurrencyExchangeDTO;
import com.agrokaszuby.backend.exception.CurrencyExchangeNotFoundException;
import com.agrokaszuby.backend.mapper.CurrencyExchangeMapper;
import com.agrokaszuby.backend.service.CurrencyExchangeDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
