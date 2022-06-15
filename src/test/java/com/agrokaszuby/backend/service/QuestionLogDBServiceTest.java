package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.QuestionLog;
import com.agrokaszuby.backend.exception.QuestionLogNotFoundException;
import com.agrokaszuby.backend.repository.QuestionLogRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionLogDBServiceTest {

    @InjectMocks
    QuestionLogDBService dbService;
    @Mock
    QuestionLogRepository repository;

    @Test
    void getAllQuestionLogs() {
        //given
        QuestionLog questionLog = new QuestionLog();
        List<QuestionLog> questionLogList = new ArrayList<>();
        questionLogList.add(questionLog);
        //when
        List<QuestionLog> allQuestionLogs = dbService.getAllQuestionLogs();
        //then
        assertEquals(0, allQuestionLogs.size());

    }

    @Test
    void getQuestionLog() throws QuestionLogNotFoundException {
        //given
        QuestionLog questionLog = new QuestionLog(1L, "fromName", "email",
                Boolean.TRUE, LocalDateTime.now());

        //when
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(questionLog));
        QuestionLog actualQuestionLog = dbService.getQuestionLog(1L);
        //then
        assertEquals(questionLog, actualQuestionLog);

    }
}