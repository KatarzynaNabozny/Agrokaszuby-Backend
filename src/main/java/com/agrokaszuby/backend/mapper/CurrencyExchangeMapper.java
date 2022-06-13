package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.CurrencyExchange;
import com.agrokaszuby.backend.domain.dto.CurrencyExchangeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyExchangeMapper {

    public CurrencyExchange mapToCurrencyExchange(final CurrencyExchangeDTO currencyExchangeDTO) {
        return new CurrencyExchange(
                currencyExchangeDTO.getCurrencyExchangeId(),
                currencyExchangeDTO.getFromCurrency(),
                currencyExchangeDTO.getToCurrency(),
                currencyExchangeDTO.getRate(),
                currencyExchangeDTO.getDate()
        );
    }

    public CurrencyExchangeDTO mapToCurrencyExchangeDTO(final CurrencyExchange currencyExchange) {
        return new CurrencyExchangeDTO(
                currencyExchange.getCurrencyExchangeId(),
                currencyExchange.getFromCurrency(),
                currencyExchange.getToCurrency(),
                currencyExchange.getRate(),
                currencyExchange.getDate()
        );
    }

    public List<CurrencyExchangeDTO> mapToCurrencyExchangeDtoList(final List<CurrencyExchange> currencyExchangeList) {
        return currencyExchangeList.stream()
                .map(this::mapToCurrencyExchangeDTO)
                .collect(Collectors.toList());
    }
}
