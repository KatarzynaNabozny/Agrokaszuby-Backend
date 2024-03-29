package com.agrokaszuby.backend.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Mail {
    private final String mailTo;
    private final String mailFrom;
    private final String subject;
    private final String message;
    private final String toCc;

}
