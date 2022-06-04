package com.agrokaszuby.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
