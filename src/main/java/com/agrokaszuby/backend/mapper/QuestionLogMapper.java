package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.QuestionLog;
import com.agrokaszuby.backend.domain.dto.QuestionLogDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionLogMapper {

    public QuestionLog mapToQuestionLog(final QuestionLogDTO questionLogDTO) {
        return new QuestionLog(
                questionLogDTO.getQuestionLogId(),
                questionLogDTO.getEmail(),
                questionLogDTO.getEvent(),
                questionLogDTO.getSuccessful(),
                questionLogDTO.getDate()
        );
    }

    public QuestionLogDTO mapToQuestionLogDTO(final QuestionLog questionLog) {
        return new QuestionLogDTO(
                questionLog.getQuestionLogId(),
                questionLog.getEmail(),
                questionLog.getEvent(),
                questionLog.getSuccessful(),
                questionLog.getDate()
        );
    }

    public List<QuestionLogDTO> mapToQuestionLogDtoList(final List<QuestionLog> questionLogList) {
        return questionLogList.stream()
                .map(this::mapToQuestionLogDTO)
                .collect(Collectors.toList());
    }
}