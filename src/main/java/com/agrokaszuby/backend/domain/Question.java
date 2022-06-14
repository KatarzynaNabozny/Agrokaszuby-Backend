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
@Entity(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID", unique = true)
    private Long questionId;

    @Column(name = "FROM_NAME")
    private String fromName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "DATE")
    private LocalDateTime date;

}
