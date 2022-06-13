package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {

    @Override
    List<CurrencyExchange> findAll();

    @Override
    Optional<CurrencyExchange> findById(Long currencyExchangeId);

    Optional<CurrencyExchange> findByFromCurrencyAndToCurrencyAndDate(
            String fromCurrency, String toCurrency, LocalDate date);

    Optional<CurrencyExchange> findByDate(LocalDate fromCurrency);

    Optional<CurrencyExchange> findByFromCurrency(String fromCurrency);

    Optional<CurrencyExchange> findByToCurrency(String fromCurrency);

    @Override
    CurrencyExchange save(CurrencyExchange currencyExchange);

    @Override
    void deleteById(Long currencyExchangeId);
}
