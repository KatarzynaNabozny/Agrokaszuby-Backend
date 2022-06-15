package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.CommentLog;
import com.agrokaszuby.backend.exception.CommentLogNotFoundException;
import com.agrokaszuby.backend.repository.CommentLogRepository;

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
class CommentLogDBServiceTest {

    @InjectMocks
    CommentLogDBService dbService;
    @Mock
    CommentLogRepository repository;

    @Test
    void getAllCommentLogs() {
        //given
        CommentLog commentLog = new CommentLog();
        List<CommentLog> commentLogList = new ArrayList<>();
        commentLogList.add(commentLog);
        //when
        List<CommentLog> allCommentLogs = dbService.getAllCommentLogs();
        //then
        assertEquals(0, allCommentLogs.size());

    }

    @Test
    void getCommentLog() throws CommentLogNotFoundException {
        //given
        CommentLog commentLog = new CommentLog(1L, "fromName", "email",
                Boolean.TRUE, LocalDateTime.now());

        //when
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(commentLog));
        CommentLog actualCommentLog = dbService.getCommentLog(1L);
        //then
        assertEquals(commentLog, actualCommentLog);

    }
}