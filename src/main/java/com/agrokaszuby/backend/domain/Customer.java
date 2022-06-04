package com.agrokaszuby.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID", unique = true)
    private Long customerId;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "JOIN_CUSTOMER_RESERVATION",
            joinColumns = {@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "RESERVATION_ID", referencedColumnName = "RESERVATION_ID")}
    )
    private List<Reservation> reservations = new ArrayList<>();
}
