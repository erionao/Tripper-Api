package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.TicketFlight;
import com.devfreaks.tripper.repositories.FlightRepository;
import com.devfreaks.tripper.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TicketFlightValidator implements Validator {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == TicketFlight.class;
    }

    public void validate(Object target, Errors errors) {

        TicketFlight tckFlight = (TicketFlight) target;

        if (flightRepository.findOne(tckFlight.getFlight().getId()) != null) {
            errors.rejectValue("flightId", "flight.id.required");
        }

        if (ticketRepository.findOne(tckFlight.getTicket().getId()) != null) {
            errors.rejectValue("ticketId", "ticket.id.required");
        }

        if (tckFlight.getOrder() != null) {
            errors.rejectValue("order", "order.required");
        }
    }


}
