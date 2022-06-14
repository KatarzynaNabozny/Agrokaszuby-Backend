package com.agrokaszuby.backend.scheduler;

import com.agrokaszuby.backend.config.AdminConfig;
import com.agrokaszuby.backend.domain.Mail;
import com.agrokaszuby.backend.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Agrokaszuby: daily Reservation report";
    private static final String MESSAGE = "We are sending all date corresponding to new reservations";

    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        simpleEmailService.send(
                Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .mailFrom(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message(MESSAGE)
                        .toCc(null)
                        .build()
        );
    }
}
