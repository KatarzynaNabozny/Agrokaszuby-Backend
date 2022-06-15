package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.Currency;
import com.agrokaszuby.backend.domain.Customer;
import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {

    public Reservation mapToReservation(final ReservationDTO reservationDTO) {

        Customer customer = new Customer(null, reservationDTO.getFirstName(),
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
                reservationDTO.getCurrency().name(),
                reservationDTO.getPrice(),
                customers
        );
    }

    public ReservationDTO mapToReservationDTO(final Reservation reservation) {

        List<Customer> customerInTheReservation = reservation.getCustomerInTheReservation();
        Optional<Customer> firstCustomer = customerInTheReservation != null ? customerInTheReservation.stream().findFirst() : null;

        if (customerInTheReservation != null && firstCustomer.isPresent()) {
            Customer customer = firstCustomer.get();
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
                    customer.getEmail(),
                    getCurrency(reservation),
                    reservation.getPrice());
        } else {
            return new ReservationDTO(
                    reservation.getReservationId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    null,
                    null,
                    null,
                    reservation.getCity(),
                    reservation.getPostalCode(),
                    reservation.getStreet(),
                    null,
                    getCurrency(reservation),
                    reservation.getPrice());
        }
    }

    private Currency getCurrency(Reservation reservation) {
        return reservation.getCurrency() != null ? Currency.valueOf(reservation.getCurrency()) : null;
    }

    public List<ReservationDTO> mapToReservationDtoList(final List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::mapToReservationDTO)
                .collect(Collectors.toList());
    }
}
