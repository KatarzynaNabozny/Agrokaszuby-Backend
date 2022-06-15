package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.Question;
import com.agrokaszuby.backend.domain.dto.QuestionDTO;
import com.agrokaszuby.backend.mapper.QuestionMapper;
import com.agrokaszuby.backend.service.QuestionDBService;
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
@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    public static final String QUESTION_ID = "questionId";
    public static final String EMAIL = "email";
    public static final String CONTENT = "content";
    public static final int ID_ONE = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionDBService dbService;
    @MockBean
    private QuestionMapper mapper;
    private String urlTemplate = "/agrokaszuby/backend/question";

    @Test
    void shouldFetchEmptyQuestionList() throws Exception {
        //Given
        final List<Question> questions = new ArrayList<>();
        //When
        when(mapper.mapToQuestionDtoList(questions)).thenReturn(List.of());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchQuestionList() throws Exception {
        //Given
        List<QuestionDTO> questionDtoList = new ArrayList<>();
        questionDtoList.add(getQuestionDTO());
        List<Question> questions = new ArrayList<>();
        questions.add(getQuestion());
        //When
        when(mapper.mapToQuestionDtoList(questions)).thenReturn(questionDtoList);
        when(dbService.getAllQuestions()).thenReturn(questions);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].questionId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is(CONTENT)));
    }

    private Question getQuestion() {
        return new Question(1L, "fromName", "email",
                "content", "subject" ,LocalDateTime.now());
    }

    private QuestionDTO getQuestionDTO() {
        return new QuestionDTO(1L, "fromName", "email",
                "content","subject", LocalDateTime.now());
    }

    @Test
    void testShouldFetchQuestion() throws Exception {
        //Given & When
        when(mapper.mapToQuestionDTO(any(Question.class))).thenReturn(getQuestionDTO());
        when(dbService.getQuestion(anyLong())).thenReturn(getQuestion());
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(urlTemplate + "/search/id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(QUESTION_ID, "1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.questionId", Matchers.is(ID_ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(EMAIL)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is(CONTENT)));
    }

    @Test
    void shouldDeleteQuestion() throws Exception {
        //When & Then
        mockMvc.perform(delete(urlTemplate + "/1").
                        param(QUESTION_ID, "1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateQuestion() throws Exception {
        //Given
        objectMapper.findAndRegisterModules();
        QuestionDTO questionDTO = getQuestionDTO();
        Question question = getQuestion();
        //When
        when(mapper.mapToQuestion(any(QuestionDTO.class))).thenReturn(question);
        when(dbService.saveQuestion(question)).thenReturn(question);
        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDTO))
                        .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, Mockito.times(1)).saveQuestion(question);
    }

}