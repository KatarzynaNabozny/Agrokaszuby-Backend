package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.CurrencyExchange;
import com.agrokaszuby.backend.exception.CurrencyExchangeNotFoundException;
import com.agrokaszuby.backend.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public CurrencyExchange getCurrencyExchangeByFromCurrency(final String fromCurrency) throws CurrencyExchangeNotFoundException {
        return repository.findByFromCurrency(fromCurrency).orElseThrow(CurrencyExchangeNotFoundException::new);
    }

    public CurrencyExchange getCurrencyExchangeByToCurrency(final String toCurrency) throws CurrencyExchangeNotFoundException {
        return repository.findByToCurrency(toCurrency).orElseThrow(CurrencyExchangeNotFoundException::new);
    }

    public CurrencyExchange getCurrencyExchangeByDate(final LocalDate date) throws CurrencyExchangeNotFoundException {
        return repository.findByDate(date).orElseThrow(CurrencyExchangeNotFoundException::new);
    }

    public CurrencyExchange getCurrencyExchangeByFromCurrencyAndToCurrencyAndDate(final String fromCurrency, final String toCurrency, final LocalDate date)
            throws CurrencyExchangeNotFoundException {
        return repository.findByFromCurrencyAndToCurrencyAndDate(fromCurrency, toCurrency, date).orElseThrow(CurrencyExchangeNotFoundException::new);
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
