package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.config.AdminConfig;
import com.agrokaszuby.backend.domain.Mail;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import com.agrokaszuby.backend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationEmailService {

    private static final String SUBJECT = "Agrokaszuby: Reservation confirmation";
    public static final String MESSAGE = "We are sending all date corresponding to new reservation";

    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;

    public void sendInformationEmail(ReservationDTO reservation) {
        simpleEmailService.sendReservationInfo(
                Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .mailFrom(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message(MESSAGE)
                        .toCc(null)
                        .build(),
                reservation
        );
    }

}
