package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.Question;
import com.agrokaszuby.backend.domain.dto.QuestionDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionMapperTest {

    @InjectMocks
    private QuestionMapper questionMapper;

    @Test
    void mapToQuestion() {
        //Given
        questionMapper = new QuestionMapper();
        QuestionDTO questionDto = QuestionDTO.builder().email("email").build();
        //When
        Question question = questionMapper.mapToQuestion(questionDto);
        //Then
        assertEquals("email", question.getEmail());

    }

    @Test
    void mapToQuestionDTO() {
        //Given
        questionMapper = new QuestionMapper();
        Question question = new Question();
        //When
        QuestionDTO questionDto = questionMapper.mapToQuestionDTO(question);
        //Then
        assertEquals(null, questionDto.getEmail());

    }

    @Test
    void mapToQuestionDTOList() {
        //Given
        questionMapper = new QuestionMapper();
        Question question = new Question(10L, "fitstName", "email","", null,null);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        //When
        List<QuestionDTO> questionDto = questionMapper.mapToQuestionDtoList(questionList);
        //Then
        assertEquals(10, questionDto.get(0).getQuestionId());
    }
}
