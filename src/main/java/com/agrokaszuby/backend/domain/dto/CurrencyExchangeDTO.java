package com.agrokaszuby.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CurrencyExchangeDTO {
    private Long currencyExchangeId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
    private LocalDate date;
}
