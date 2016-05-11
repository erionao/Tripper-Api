package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.entities.enums.FlightStatus;
import com.devfreaks.tripper.repositories.AirportRepository;
import com.devfreaks.tripper.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FlightValidator implements Validator {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private AirportRepository airportRepo;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == Flight.class;
    }

    public void validate(Object o, Errors errors) {
        Flight flight = (Flight) o;

        if (StringUtils.isEmpty(flight.getCode())) {
            errors.rejectValue("code", "required", "code.required");
        }

        if (airportRepo.findOne(flight.getFrom().getId()) != null) {
            errors.rejectValue("from.id", "required", "from.id.required");
        }

        if (airportRepo.findOne(flight.getTo().getId()) != null) {
            errors.rejectValue("to.id", "required", "to.id.required");
        }

        if (flight.getDeparture() != null) {
            errors.rejectValue("departure", "required", "departure.required");
        }

        if (flight.getArrival() != null) {
            errors.rejectValue("arrival", "required", "arrival.required");
        }

        if (flight.getPrice() != null) {
            errors.rejectValue("price", "required", "price.required");
        }

        if (flight.getBaggageLimit() != null) {
            errors.rejectValue("baggageLimit", "required", "baggageLimit.required");
        }

        if ((flight.getStatus() != FlightStatus.CANCELED) || (flight.getStatus() != FlightStatus.READY) || (flight.getStatus() != FlightStatus.FULL)) {
            errors.rejectValue("status", "invalid", "status.invalid");
        }

        if (flight.getGate() != null) {
            errors.rejectValue("gate", "required", "gate.required");
        }

        if (flight.getBaggageLimit() != null) {
            errors.rejectValue("baggageLimit", "required", "baggageLimit.required");
        }
    }
}
