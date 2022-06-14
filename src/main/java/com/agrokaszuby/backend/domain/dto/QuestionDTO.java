package com.agrokaszuby.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class QuestionDTO {
    private Long questionId;
    private String fromName;
    private String email;
    private String content;
    private String subject;
    private LocalDateTime date;
}
