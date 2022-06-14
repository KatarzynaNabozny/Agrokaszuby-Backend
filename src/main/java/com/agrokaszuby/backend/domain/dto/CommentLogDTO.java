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
public class CommentLogDTO {
    private Long commentLogId;
    private String email;
    private String event;
    private Boolean successful;
    private LocalDateTime date;
}
