package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.ReservationLog;
import com.agrokaszuby.backend.domain.dto.ReservationLogDTO;
import com.agrokaszuby.backend.mapper.ReservationLogMapper;
import com.agrokaszuby.backend.service.ReservationLogDBService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(ReservationLogController.class)
class ReservationLogControllerTest {

    public static final String RESERVATION_LOG_ID = "reservationLogId";
    public static final String EMAIL = "email";
    public static final String SAVE = "SAVE";
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationLogDBService dbService;
    @MockBean
    private ReservationLogMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/reservation_log";

    @Test
    void shouldFetchEmptyReservationLogList() throws Exception {
        //Given
        final List<ReservationLog> reservationLogs = new ArrayList<>();
        //When
        when(mapper.mapToReservationLogDtoList(reservationLogs)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchReservationLogList() throws Exception {
        //Given
        List<ReservationLogDTO> reservationLogDtoList = new ArrayList<>();
        reservationLogDtoList.add(getReservationLogDTO());
        List<ReservationLog> reservationLogs = new ArrayList<>();
        reservationLogs.add(getReservationLog());
        //When
        when(mapper.mapToReservationLogDtoList(reservationLogs)).thenReturn(reservationLogDtoList);
        when(dbService.getAllReservationLogs()).thenReturn(reservationLogs);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reservationLogId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].event", Matchers.is("SAVE")));
    }

    private ReservationLog getReservationLog() {
        return new ReservationLog(1L, "email", "SAVE", Boolean.TRUE, LocalDateTime.now());
    }

    private ReservationLogDTO getReservationLogDTO() {
        return new ReservationLogDTO(1L, "email", "SAVE", Boolean.TRUE, LocalDateTime.now());
    }

    @Test
    void testShouldFetchReservationLog() throws Exception {
        //Given & When
        when(mapper.mapToReservationLogDTO(any(ReservationLog.class))).thenReturn(getReservationLogDTO());
        when(dbService.getReservationLog(anyLong())).thenReturn(getReservationLog());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(RESERVATION_LOG_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationLogId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.event", Matchers.is(SAVE)));
    }

    @Test
    void shouldDeleteReservationLog() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(RESERVATION_LOG_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateReservationLog() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        ReservationLogDTO reservationLogDTO = getReservationLogDTO();
        ReservationLog reservationLog = getReservationLog();
        //When
        when(mapper.mapToReservationLog(any(ReservationLogDTO.class))).thenReturn(reservationLog);
        when(dbService.saveReservationLog(reservationLog)).thenReturn(reservationLog);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationLogDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveReservationLog(reservationLog);
    }

}