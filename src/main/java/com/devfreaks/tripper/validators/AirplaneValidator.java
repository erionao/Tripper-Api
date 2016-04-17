package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.entities.QAirplane;
import com.devfreaks.tripper.repositories.AirlineRepository;
import com.devfreaks.tripper.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AirplaneValidator implements Validator {

    @Autowired
    private AirplaneRepository repository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Airplane.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Airplane airplane = (Airplane) target;

        if (StringUtils.isEmpty(airplane.getCode())) {
            errors.rejectValue("code", "airplane.code.required");
        } else if (repository.findOne(QAirplane.airplane.code.eq(airplane.getCode())) != null) {
            errors.rejectValue("code", "airplane.code.exists");
        }

        if (StringUtils.isEmpty(airplane.getName())) {
            errors.rejectValue("name", "airplane.name.required");
        }else if (repository.findOne(QAirplane.airplane.name.eq(airplane.getName())) != null) {
            errors.rejectValue("code", "airplane.name.exists");
        }

        if(airplane.getSeats() != null){
            errors.rejectValue("seats", "airplane.seats.required");
        }

        if(airlineRepository.findOne(airplane.getAirline().getId()) != null){
            errors.rejectValue("airline_id", "airplane.airline_id.required");
        }

    }
}
