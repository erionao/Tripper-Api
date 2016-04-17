package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.Airline;
import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AirlineValidator implements Validator {

    @Autowired
    private AirlineRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Country.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Airline airline = (Airline) target;

        if (StringUtils.isEmpty(airline.getName())) {
            errors.rejectValue("name", "airline.name.required");
        }
    }
}
