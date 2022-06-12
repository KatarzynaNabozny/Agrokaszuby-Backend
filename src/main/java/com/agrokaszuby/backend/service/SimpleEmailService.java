package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Mail;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    private MimeMessagePreparator createMessage(final Mail mail, ReservationDTO reservation) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setFrom(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildReservationEmail(mail.getMessage(), reservation), true);
        };
    }

    public void sendReservationInfo(final Mail mail, ReservationDTO reservation) {
        log.info("RESERVATION INFO - Starting email preparation...");
        try {
            javaMailSender.send(createMessage(mail, reservation));
            log.info("RESERVATION INFO - Email has been sent.");
        } catch (MailException e) {
            log.error("RESERVATION INFO - Failed to process email sending: " + e.getMessage(), e);
        }
    }
}
