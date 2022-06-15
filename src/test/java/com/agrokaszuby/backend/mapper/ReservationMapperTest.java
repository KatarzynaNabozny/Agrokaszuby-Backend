package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.Currency;
import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservationMapperTest {

    @InjectMocks
    private ReservationMapper reservationMapper;

    @Test
    void mapToReservation() {
        //Given
        reservationMapper = new ReservationMapper();
        ReservationDTO reservationDto = ReservationDTO.builder().currency(Currency.PLN).city("city").build();
        //When
        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        //Then
        assertEquals("city", reservation.getCity());

    }

    @Test
    void mapToReservationDTO() {
        //Given
        reservationMapper = new ReservationMapper();
        Reservation reservation = new Reservation();
        //When
        ReservationDTO reservationDto = reservationMapper.mapToReservationDTO(reservation);
        //Then
        assertEquals(null, reservationDto.getCity());

    }

    @Test
    void mapToReservationDTOList() {
        //Given
        reservationMapper = new ReservationMapper();
        Reservation reservation = new Reservation(10L, null,
                null,"city", "street 12",
                "098989",null,null,null);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        //When
        List<ReservationDTO> reservationDto = reservationMapper.mapToReservationDtoList(reservationList);
        //Then
        assertEquals(10, reservationDto.get(0).getReservationId());
    }
}
