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
            errors.rejectValue("code", "required", "Code is required");
        } else if (ticket.getId() == null && repository.findOne(QTicket.ticket.code.eq(ticket.getCode())) != null) {
            errors.rejectValue("code", "exists", "Code is taken");
        } else if (ticket.getId() != null && repository.findOne(ticket.getId()) != null && repository.findOne(QTicket.ticket.code.eq(ticket.getCode())) != null) {
            errors.rejectValue("code", "exists", "Code is taken");
        }

        if (StringUtils.isEmpty(ticket.getFullName())) {
            errors.rejectValue("fullName", "required", "Full name is requried");
        }

        if (ticket.getBirthday() == null) {
            errors.rejectValue("birthday", "required", "Birthday is required");
        }

        if (StringUtils.isEmpty(ticket.getPassportNo())) {
            errors.rejectValue("passportNo", "required", "Passport number is required");
        }

        if (userRepository.findOne(ticket.getUser().getId()) == null) {
            errors.rejectValue("user.id", "required", "User is required");
        }
    }
}
