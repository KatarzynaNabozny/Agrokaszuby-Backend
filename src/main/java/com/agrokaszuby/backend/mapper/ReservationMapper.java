package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {

    public Reservation mapToReservation(final ReservationDTO reservationDTO) {
        return new Reservation(
                reservationDTO.getReservationId(),
                reservationDTO.getStartDate(),
                reservationDTO.getEndDate(),
                reservationDTO.getFirstName(),
                reservationDTO.getLastName(),
                reservationDTO.getPhoneNumber(),
                reservationDTO.getCity(),
                reservationDTO.getPostalCode(),
                reservationDTO.getStreet(),
                reservationDTO.getEmail()
        );
    }

    public ReservationDTO mapToReservationDTO(final Reservation reservation) {
        return new ReservationDTO(
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getFirstName(),
                reservation.getLastName(),
                reservation.getPhoneNumber(),
                reservation.getCity(),
                reservation.getPostalCode(),
                reservation.getStreet(),
                reservation.getEmail()
        );
    }

    public List<ReservationDTO> mapToReservationDtoList(final List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::mapToReservationDTO)
                .collect(Collectors.toList());
    }
}
