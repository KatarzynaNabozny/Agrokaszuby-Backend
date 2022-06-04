package com.agrokaszuby.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID", unique = true)
    private Long reservationId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ReservationHasCustomers",
            joinColumns = {@JoinColumn(name = "RESERVATION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CUSTOMER_ID")}
    )
    private List<Customer> customerInTheReservation = new ArrayList<>();

}

