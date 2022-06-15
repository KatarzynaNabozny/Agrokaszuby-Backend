package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.Currency;
import com.agrokaszuby.backend.domain.Reservation;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import com.agrokaszuby.backend.mapper.ReservationMapper;
import com.agrokaszuby.backend.service.ReservationDBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    public static final String RESERVATION_ID = "reservationId";
    public static final String STREET = "street";
    public static final String CITY = "city";
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationDBService dbService;
    @MockBean
    private ReservationMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/reservation";

    @Test
    void shouldFetchEmptyReservationList() throws Exception {
        //Given
        final List<Reservation> reservations = new ArrayList<>();
        //When
        when(mapper.mapToReservationDtoList(reservations)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchReservationList() throws Exception {
        //Given
        List<ReservationDTO> reservationDtoList = new ArrayList<>();
        reservationDtoList.add(getReservationDTO());
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(getReservation());
        //When
        when(mapper.mapToReservationDtoList(reservations)).thenReturn(reservationDtoList);
        when(dbService.getAllReservations()).thenReturn(reservations);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reservationId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].street", Matchers.is(STREET)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city", Matchers.is(CITY)));
    }

    private Reservation getReservation() {
        return new Reservation(1L, null, null,
                CITY, STREET, "", Currency.PLN.name(), null, null);
    }

    private ReservationDTO getReservationDTO() {
        return ReservationDTO.builder()
                .reservationId(1L)
                .street(STREET)
                .city(CITY)
                .currency(Currency.PLN)
                .build();
    }

    @Test
    void testShouldFetchReservation() throws Exception {
        //Given & When
        when(mapper.mapToReservationDTO(any(Reservation.class))).thenReturn(getReservationDTO());
        when(dbService.getReservation(anyLong())).thenReturn(getReservation());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(RESERVATION_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street", Matchers.is(STREET)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is(CITY)));
    }

    @Test
    void shouldDeleteReservation() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(RESERVATION_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateReservation() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        ReservationDTO reservationDTO = getReservationDTO();
        Reservation reservation = getReservation();
        //When
        when(mapper.mapToReservation(any(ReservationDTO.class))).thenReturn(reservation);
        when(dbService.saveReservation(reservation)).thenReturn(reservation);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveReservation(reservation);
    }

}