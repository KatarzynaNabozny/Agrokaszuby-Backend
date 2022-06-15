package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.CommentLog;
import com.agrokaszuby.backend.domain.dto.CommentLogDTO;
import com.agrokaszuby.backend.mapper.CommentLogMapper;
import com.agrokaszuby.backend.service.CommentLogDBService;
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
@WebMvcTest(CommentLogController.class)
class CommentLogControllerTest {

    public static final String COMMENT_LOG_ID = "commentLogId";
    public static final String EMAIL = "email";
    public static final String SAVE = "SAVE";
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentLogDBService dbService;
    @MockBean
    private CommentLogMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/comment_log";

    @Test
    void shouldFetchEmptyCommentLogList() throws Exception {
        //Given
        final List<CommentLog> commentLogs = new ArrayList<>();
        //When
        when(mapper.mapToCommentLogDtoList(commentLogs)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchCommentLogList() throws Exception {
        //Given
        List<CommentLogDTO> commentLogDtoList = new ArrayList<>();
        commentLogDtoList.add(getCommentLogDTO());
        List<CommentLog> commentLogs = new ArrayList<>();
        commentLogs.add(getCommentLog());
        //When
        when(mapper.mapToCommentLogDtoList(commentLogs)).thenReturn(commentLogDtoList);
        when(dbService.getAllCommentLogs()).thenReturn(commentLogs);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].commentLogId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].event", Matchers.is("SAVE")));
    }

    private CommentLog getCommentLog() {
        return new CommentLog(1L, "email", "SAVE", Boolean.TRUE, LocalDateTime.now());
    }

    private CommentLogDTO getCommentLogDTO() {
        return new CommentLogDTO(1L, "email", "SAVE", Boolean.TRUE, LocalDateTime.now());
    }

    @Test
    void testShouldFetchCommentLog() throws Exception {
        //Given & When
        when(mapper.mapToCommentLogDTO(any(CommentLog.class))).thenReturn(getCommentLogDTO());
        when(dbService.getCommentLog(anyLong())).thenReturn(getCommentLog());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(COMMENT_LOG_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentLogId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.event", Matchers.is(SAVE)));
    }

    @Test
    void shouldDeleteCommentLog() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(COMMENT_LOG_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateCommentLog() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        CommentLogDTO commentLogDTO = getCommentLogDTO();
        CommentLog commentLog = getCommentLog();
        //When
        when(mapper.mapToCommentLog(any(CommentLogDTO.class))).thenReturn(commentLog);
        when(dbService.saveCommentLog(commentLog)).thenReturn(commentLog);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentLogDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveCommentLog(commentLog);
    }

}