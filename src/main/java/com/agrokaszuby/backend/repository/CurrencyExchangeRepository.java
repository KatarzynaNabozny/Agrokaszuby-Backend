package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {

    @Override
    List<CurrencyExchange> findAll();

    @Override
    Optional<CurrencyExchange> findById(Long currencyExchangeId);

    @Override
    CurrencyExchange save(CurrencyExchange currencyExchange);

    @Override
    void deleteById(Long currencyExchangeId);
}
