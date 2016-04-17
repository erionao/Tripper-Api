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
            errors.rejectValue("ticket", "ticket.code.required");
        } else if (repository.findOne(QTicket.ticket.code.eq(ticket.getCode())) != null) {
            errors.rejectValue("code", "ticket.code.exists");
        }

        if (StringUtils.isEmpty(ticket.getFullName())) {
            errors.rejectValue("full_name", "ticket.full_name.required");
        }

        if(ticket.getBirthday() != null){
            errors.rejectValue("birthday", "ticket.birthday.required");
        }

        if (StringUtils.isEmpty(ticket.getPassportNo())) {
            errors.rejectValue("passport_no", "ticket.passport_no.required");
        }

        if(userRepository.findOne(ticket.getUser().getId()) != null){
            errors.rejectValue("user_id", "airport.user_id.required");
        }


    }
}
