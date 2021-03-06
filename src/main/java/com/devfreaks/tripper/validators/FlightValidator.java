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
            errors.rejectValue("code", "required", "Code is required");
        }

        if (flight.getFrom() == null || airportRepo.findOne(flight.getFrom().getId()) == null) {
            errors.rejectValue("from.id", "required", "From is required");
        }

        if (flight.getTo() == null || airportRepo.findOne(flight.getTo().getId()) == null) {
            errors.rejectValue("to.id", "required", "To is required");
        }

        if (flight.getDeparture() == null) {
            errors.rejectValue("departure", "required", "Departure is required");
        }

        if (flight.getArrival() == null) {
            errors.rejectValue("arrival", "required", "Arrival is required");
        }

        if (flight.getPrice() == null) {
            errors.rejectValue("price", "required", "Price is required");
        }

        if (flight.getStatus() == null) {
            errors.rejectValue("status", "required", "Status is required");
        }
    }
}
