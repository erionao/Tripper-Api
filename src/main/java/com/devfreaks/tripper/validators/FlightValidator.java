package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.entities.enums.FlightStatus;
import com.devfreaks.tripper.repositories.AirplaneRepository;
import com.devfreaks.tripper.repositories.AirportRepository;
import com.devfreaks.tripper.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import java.util.Date;


@Component
public class FlightValidator implements Validator {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private AirportRepository airportRepo;

    @Autowired
    private AirplaneRepository airplaneRepo;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == Flight.class;
    }

    public void validate(Object o, Errors errors){
        Flight flight = (Flight)o;

        if (StringUtils.isEmpty(flight.getCode())) {
            errors.rejectValue("code", "required", "flight.code.required");
        }

        if(airportRepo.findOne(flight.getFrom().getId()) != null){
            errors.rejectValue("from_airport", "required", "flight.from_airport.required");
        }

        if (airportRepo.findOne(flight.getTo().getId()) != null) {
            errors.rejectValue("to_airport", "required", "flight.to_airport.required");
        }

        if(flight.getDeparture() != null){
            errors.rejectValue("departure", "required", "flight.departure.required");
        }

        if(flight.getArrival() != null){
            errors.rejectValue("arrival", "required", "flight.arrival.required");
        }

        if (flight.getPrice() != null){
            errors.rejectValue("price", "required", "flight.price.required");
        }

        if (flight.getBaggageLimit() != null){
            errors.rejectValue("baggageLimit", "required", "flight.baggageLimit.required");
        }

        if ((flight.getStatus() != FlightStatus.CANCELED) ||(flight.getStatus() !=FlightStatus.READY) ||(flight.getStatus() !=  FlightStatus.FULL)){
            errors.rejectValue("status", "invalid", "flight.status.invalid");
        }

        if (flight.getGate() != null){
            errors.rejectValue("gate", "required", "flight.gate.required");
        }


        if (flight.getBaggageLimit() != null){
            errors.rejectValue("baggageLimit", "required", "flight.baggageLimit.required");
        }

        if(airplaneRepo.findOne(flight.getAirplane().getId()) != null){
            errors.rejectValue("airplane", "required", "flight.airplane.required");
        }

    }







}
