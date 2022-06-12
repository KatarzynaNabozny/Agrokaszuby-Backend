package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.config.AdminConfig;
import com.agrokaszuby.backend.domain.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildReservationEmail(String message, ReservationDTO reservation) {
        boolean isFriend;
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8080/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_mail", adminConfig.getCompanyMail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("reservation", reservation);

        if (reservation.getEmail().equals(adminConfig.getAdminMail())) {
            isFriend = true;
        } else {
            isFriend = false;
        }
        context.setVariable("is_friend", isFriend);
        return templateEngine.process("mail/agrokaszuby-reservation-mail", context);
    }

}
