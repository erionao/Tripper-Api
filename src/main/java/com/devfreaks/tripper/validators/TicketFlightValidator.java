package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.repositories.TicketRepository;
import com.devfreaks.tripper.repositories.FlightRepository;
import com.devfreaks.tripper.entities.TicketFlight;
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
    public boolean supports(Class<?> clazz){
        return clazz == TicketFlight.class;
    }

    public void validate(Object target, Errors errors){

        TicketFlight tckFlight = (TicketFlight) target;

        if(flightRepository.findOne(tckFlight.getFlight().getId()) != null){
            errors.rejectValue("flight_id", "ticketFlight.flight_id.required");
        }

        if(ticketRepository.findOne(tckFlight.getTicket().getId()) != null){
            errors.rejectValue("ticket_id", "ticketFlight.ticket_id.required");
        }

        if (tckFlight.getOrder() != null){
            errors.rejectValue("order", "ticketFlight.order.required");
        }
    }




}
