package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.config.AdminConfig;
import com.agrokaszuby.backend.domain.Mail;
import com.agrokaszuby.backend.scheduler.EmailScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

    private static final String SUBJECT = "Agrokaszuby: daily Reservation report";
    private static final String MESSAGE = "We are sending all date corresponding to new reservations";

    @Mock
    AdminConfig adminConfig;
    @Mock
    SimpleEmailService simpleEmailService;
    @InjectMocks
    EmailScheduler emailScheduler;

    @Test
    void sendInformationEmail() {
        //Given
        emailScheduler = new EmailScheduler(simpleEmailService, adminConfig);
        Mail mail = Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .mailFrom(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message(MESSAGE)
                .toCc(null)
                .build();
        //When
        emailScheduler.sendInformationEmail();
        //Then
        verify(simpleEmailService,times(1)).send(mail);
    }

}