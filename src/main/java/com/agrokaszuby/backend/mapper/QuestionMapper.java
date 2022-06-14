package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.Question;
import com.agrokaszuby.backend.domain.dto.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionMapper {

    public Question mapToQuestion(final QuestionDTO questionDTO) {
        return new Question(
                questionDTO.getQuestionId(),
                questionDTO.getFromName(),
                questionDTO.getEmail(),
                questionDTO.getContent(),
                questionDTO.getSubject(),
                questionDTO.getDate()
        );
    }

    public QuestionDTO mapToQuestionDTO(final Question question) {
        return new QuestionDTO(
                question.getQuestionId(),
                question.getFromName(),
                question.getEmail(),
                question.getContent(),
                question.getSubject(),
                question.getDate()
        );
    }

    public List<QuestionDTO> mapToQuestionDtoList(final List<Question> questionList) {
        return questionList.stream()
                .map(this::mapToQuestionDTO)
                .collect(Collectors.toList());
    }
}