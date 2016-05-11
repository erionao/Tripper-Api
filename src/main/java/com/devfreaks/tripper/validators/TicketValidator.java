package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.QTicket;
import com.devfreaks.tripper.entities.Ticket;
import com.devfreaks.tripper.repositories.TicketRepository;
import com.devfreaks.tripper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TicketValidator implements Validator {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Ticket.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Ticket ticket = (Ticket) target;

        if (StringUtils.isEmpty(ticket.getCode())) {
            errors.rejectValue("ticket", "code.required");
        } else if (repository.findOne(QTicket.ticket.code.eq(ticket.getCode())) != null) {
            errors.rejectValue("code", "code.exists");
        }

        if (StringUtils.isEmpty(ticket.getFullName())) {
            errors.rejectValue("fullName", "fullName.required");
        }

        if (ticket.getBirthday() != null) {
            errors.rejectValue("birthday", "birthday.required");
        }

        if (StringUtils.isEmpty(ticket.getPassportNo())) {
            errors.rejectValue("passportNo", "passportNo.required");
        }

        if (userRepository.findOne(ticket.getUser().getId()) != null) {
            errors.rejectValue("user.id", "user.id.required");
        }


    }
}
