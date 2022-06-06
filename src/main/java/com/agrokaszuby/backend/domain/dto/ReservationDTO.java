package com.agrokaszuby.backend.domain.dto;

import com.agrokaszuby.backend.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReservationDTO {
    private Long reservationId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String postalCode;
    private String street;
    private String email;
    private Currency currency;
    private BigDecimal price;
}
