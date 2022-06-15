package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.QuestionLog;
import com.agrokaszuby.backend.domain.dto.QuestionLogDTO;
import com.agrokaszuby.backend.mapper.QuestionLogMapper;
import com.agrokaszuby.backend.service.QuestionLogDBService;
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
@WebMvcTest(QuestionLogController.class)
class QuestionLogControllerTest {

    public static final String QUESTION_LOG_ID = "questionLogId";
    public static final String EMAIL = "email";
    public static final String SAVE = "SAVE";
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionLogDBService dbService;
    @MockBean
    private QuestionLogMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/question_log";

    @Test
    void shouldFetchEmptyQuestionLogList() throws Exception {
        //Given
        final List<QuestionLog> questionLogs = new ArrayList<>();
        //When
        when(mapper.mapToQuestionLogDtoList(questionLogs)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchQuestionLogList() throws Exception {
        //Given
        List<QuestionLogDTO> questionLogDtoList = new ArrayList<>();
        questionLogDtoList.add(getQuestionLogDTO());
        List<QuestionLog> questionLogs = new ArrayList<>();
        questionLogs.add(getQuestionLog());
        //When
        when(mapper.mapToQuestionLogDtoList(questionLogs)).thenReturn(questionLogDtoList);
        when(dbService.getAllQuestionLogs()).thenReturn(questionLogs);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].questionLogId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].event", Matchers.is("SAVE")));
    }

    private QuestionLog getQuestionLog() {
        return new QuestionLog(1L, "email", "SAVE", Boolean.TRUE, LocalDateTime.now());
    }

    private QuestionLogDTO getQuestionLogDTO() {
        return new QuestionLogDTO(1L, "email", "SAVE", Boolean.TRUE, LocalDateTime.now());
    }

    @Test
    void testShouldFetchQuestionLog() throws Exception {
        //Given & When
        when(mapper.mapToQuestionLogDTO(any(QuestionLog.class))).thenReturn(getQuestionLogDTO());
        when(dbService.getQuestionLog(anyLong())).thenReturn(getQuestionLog());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(QUESTION_LOG_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.questionLogId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.event", Matchers.is(SAVE)));
    }

    @Test
    void shouldDeleteQuestionLog() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(QUESTION_LOG_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateQuestionLog() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        QuestionLogDTO questionLogDTO = getQuestionLogDTO();
        QuestionLog questionLog = getQuestionLog();
        //When
        when(mapper.mapToQuestionLog(any(QuestionLogDTO.class))).thenReturn(questionLog);
        when(dbService.saveQuestionLog(questionLog)).thenReturn(questionLog);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionLogDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveQuestionLog(questionLog);
    }

}