package com.agrokaszuby.backend.controller;


import com.agrokaszuby.backend.domain.ReservationDTO;
import com.agrokaszuby.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agrokaszuby/backend")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationDTO> findAllReservations() {
        return reservationService.findAllReservations();
    }

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }
}
