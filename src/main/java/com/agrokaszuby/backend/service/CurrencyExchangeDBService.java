package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.CurrencyExchange;
import com.agrokaszuby.backend.exception.CurrencyExchangeNotFoundException;
import com.agrokaszuby.backend.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeDBService {

    private final CurrencyExchangeRepository repository;

    public List<CurrencyExchange> getAllCurrencyExchanges() {
        return repository.findAll();
    }

    public CurrencyExchange getCurrencyExchange(final Long currencyExchangeId) throws CurrencyExchangeNotFoundException {
        return repository.findById(currencyExchangeId).orElseThrow(CurrencyExchangeNotFoundException::new);
    }

    public CurrencyExchange saveCurrencyExchange(final CurrencyExchange reservation) {
        return repository.save(reservation);
    }

    public void deleteCurrencyExchange(final Long currencyExchangeId) throws CurrencyExchangeNotFoundException {
        try {
            repository.deleteById(currencyExchangeId);
        } catch (Exception e) {
            throw new CurrencyExchangeNotFoundException();
        }
    }
}
