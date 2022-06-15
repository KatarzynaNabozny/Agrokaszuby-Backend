package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.Comment;
import com.agrokaszuby.backend.domain.dto.CommentDTO;
import com.agrokaszuby.backend.mapper.CommentMapper;
import com.agrokaszuby.backend.service.CommentDBService;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    public static final String COMMENT_ID = "commentId";
    public static final String EMAIL = "email";
    public static final String CONTENT = "content";
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentDBService dbService;
    @MockBean
    private CommentMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/comment";

    @Test
    void shouldFetchEmptyCommentList() throws Exception {
        //Given
        final List<Comment> comments = new ArrayList<>();
        //When
        when(mapper.mapToCommentDtoList(comments)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchCommentList() throws Exception {
        //Given
        List<CommentDTO> commentDtoList = new ArrayList<>();
        commentDtoList.add(getCommentDTO());
        List<Comment> comments = new ArrayList<>();
        comments.add(getComment());
        //When
        when(mapper.mapToCommentDtoList(comments)).thenReturn(commentDtoList);
        when(dbService.getAllComments()).thenReturn(comments);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].commentId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is(CONTENT)));
    }

    private Comment getComment() {
        return new Comment(1L, "fromName", "email",
                "content", "subject", LocalDateTime.now());
    }

    private List<Comment> getComments() {
        return Arrays.asList(new Comment(1L, "fromName", "email",
                "content", "subject", LocalDateTime.now()));
    }

    private CommentDTO getCommentDTO() {
        return new CommentDTO(1L, "fromName", "email",
                "content", "subject", LocalDateTime.now());
    }

    @Test
    void testShouldFetchComment() throws Exception {
        //Given & When
        when(mapper.mapToCommentDTO(any(Comment.class))).thenReturn(getCommentDTO());
        when(dbService.getComment(anyLong())).thenReturn(getComment());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/search/id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(COMMENT_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is(CONTENT)));

    }

//    @Test
//    void testShouldFetchCommentByEmail() throws Exception {
//        //Given & When
//        when(mapper.mapToCommentDTO(any(Comment.class))).thenReturn(getCommentDTO());
//        when(dbService.getCommentByEmail(anyString())).thenReturn(getComments());
//        //Then
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(urlTemplate + "/search/email/email")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].commentId", Matchers.is(ID_ONE)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is(EMAIL)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is(CONTENT)));
//
////        get(urlTemplate + "/search/email/email")
//
//}





    //http://localhost:8090/agrokaszuby/backend/comment/search/email/knnabozny@gmail.com


    @Test
    void shouldDeleteComment() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(COMMENT_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateComment() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        CommentDTO commentDTO = getCommentDTO();
        Comment comment = getComment();
        //When
        when(mapper.mapToComment(any(CommentDTO.class))).thenReturn(comment);
        when(dbService.saveComment(comment)).thenReturn(comment);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveComment(comment);
    }

}