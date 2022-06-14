package com.agrokaszuby.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "QUESTION_LOG")
public class QuestionLog {

    @Id
    @GeneratedValue
    @Column(name = "QUESTION_LOG_ID", unique = true)
    private Long questionLogId;

    @Column(name = "EMAIL")
    private String email;
    @Column(name = "EVENT")
    private String event;
    @Column(name = "SUCCESSFUL")
    private Boolean successful;
    @Column(name = "DATE")
    private LocalDateTime date;

}
