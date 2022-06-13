package com.agrokaszuby.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CURRENCY_EXCHANGE")
public class CurrencyExchange {

    @Id
    @GeneratedValue
    @Column(name = "CURRENCY_EXCHANGE_ID", unique = true)
    private Long currencyExchangeId;

    @Column(name = "FROM_CURRENCY")
    private String fromCurrency;
    @Column(name = "TO_CURRENCY")
    private String toCurrency;
    @Column(name = "RATE")
    private BigDecimal rate;
    @Column(name = "DATE")
    private LocalDate date;

}
