package com.agrokaszuby.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.mail}")
    private String companyMail;

    @Value("${info.company.phone}")
    private String companyPhone;

}
