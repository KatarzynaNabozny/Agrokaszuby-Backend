package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.QuestionLog;
import com.agrokaszuby.backend.domain.dto.QuestionLogDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionLogMapperTest {

    @InjectMocks
    private QuestionLogMapper questionLogMapper;

    @Test
    void mapToQuestionLog() {
        //Given
        questionLogMapper = new QuestionLogMapper();
        QuestionLogDTO questionLogDto = QuestionLogDTO.builder().email("email").build();
        //When
        QuestionLog questionLog = questionLogMapper.mapToQuestionLog(questionLogDto);
        //Then
        assertEquals("email", questionLog.getEmail());

    }

    @Test
    void mapToQuestionLogDTO() {
        //Given
        questionLogMapper = new QuestionLogMapper();
        QuestionLog questionLog = new QuestionLog();
        //When
        QuestionLogDTO questionLogDto = questionLogMapper.mapToQuestionLogDTO(questionLog);
        //Then
        assertEquals(null, questionLogDto.getEmail());

    }

    @Test
    void mapToQuestionLogDTOList() {
        //Given
        questionLogMapper = new QuestionLogMapper();
        QuestionLog questionLog = new QuestionLog(10L, "email", null, Boolean.TRUE, null);
        List<QuestionLog> questionLogList = new ArrayList<>();
        questionLogList.add(questionLog);
        //When
        List<QuestionLogDTO> questionLogDto = questionLogMapper.mapToQuestionLogDtoList(questionLogList);
        //Then
        assertEquals(10, questionLogDto.get(0).getQuestionLogId());
    }
}
