package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Comment;
import com.agrokaszuby.backend.exception.CommentNotFoundException;
import com.agrokaszuby.backend.repository.CommentRepository;

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
class CommentDBServiceTest {

    @InjectMocks
    CommentDBService dbService;
    @Mock
    CommentRepository repository;

    @Test
    void getAllComments() {
        //given
        Comment comment = new Comment();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        //when
        List<Comment> allComments = dbService.getAllComments();
        //then
        assertEquals(0, allComments.size());

    }

    @Test
    void getComment() throws CommentNotFoundException {
        //given
        Comment comment = new Comment(1L, "fromName", "email",
                "content", "subject", LocalDateTime.now());
        //when
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(comment));
        Comment actualComment = dbService.getComment(1L);
        //then
        assertEquals(comment, actualComment);

    }
}