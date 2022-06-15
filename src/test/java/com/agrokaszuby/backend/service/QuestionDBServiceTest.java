package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Question;
import com.agrokaszuby.backend.exception.QuestionNotFoundException;
import com.agrokaszuby.backend.repository.QuestionRepository;

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
class QuestionDBServiceTest {

    @InjectMocks
    QuestionDBService dbService;
    @Mock
    QuestionRepository repository;

    @Test
    void getAllQuestions() {
        //given
        Question question = new Question();
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        //when
        List<Question> allQuestions = dbService.getAllQuestions();
        //then
        assertEquals(0, allQuestions.size());

    }

    @Test
    void getQuestion() throws QuestionNotFoundException {
        //given
        Question question = new Question(1L, "fromName", "email",
                "content", "subject", LocalDateTime.now());
        //when
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(question));
        Question actualQuestion = dbService.getQuestion(1L);
        //then
        assertEquals(question, actualQuestion);

    }
}