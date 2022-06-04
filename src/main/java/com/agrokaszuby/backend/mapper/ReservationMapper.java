package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.Customer;
import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {

    public Reservation mapToReservation(final ReservationDTO reservationDTO) {

        Customer customer = new Customer(null,reservationDTO.getFirstName(),
                reservationDTO.getLastName(), reservationDTO.getPhoneNumber(),
                reservationDTO.getEmail(), null);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        return new Reservation(
                reservationDTO.getReservationId(),
                reservationDTO.getStartDate(),
                reservationDTO.getEndDate(),
                reservationDTO.getCity(),
                reservationDTO.getStreet(),
                reservationDTO.getPostalCode(),
                customers
        );
    }

    public ReservationDTO mapToReservationDTO(final Reservation reservation) {

        Customer customer = reservation.getCustomerInTheReservation().stream().findFirst().get();

        return new ReservationDTO(
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                reservation.getCity(),
                reservation.getPostalCode(),
                reservation.getStreet(),
                customer.getEmail()
        );
    }
    public List<ReservationDTO> mapToReservationDtoList(final List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::mapToReservationDTO)
                .collect(Collectors.toList());
    }
}
