package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.ReservationDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private List<ReservationDTO> reservations;
    private static ReservationService movieService;

    private ReservationService() {
        this.reservations = this.reservations = exampleData();;
    }

    public static ReservationService getInstance() {
        if (movieService == null) {
            movieService = new ReservationService();
        }
        return movieService;
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        reservations.add(reservationDTO);
        return reservationDTO;
    }

    private List<ReservationDTO> exampleData() {
        List<ReservationDTO> reservations = new ArrayList<>();
        reservations.add(new ReservationDTO(LocalDateTime.now(),LocalDateTime.now().plusDays(5),
                "Katarzyna","Kowalska","124331","Szemud",
                "84-217","Towarowa 15","some@email"));
        return reservations;
    }

    public List<ReservationDTO> findAllReservations() {
        return reservations;
    }
}
