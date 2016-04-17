package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.entities.QAirport;
import com.devfreaks.tripper.entities.QCountry;
import com.devfreaks.tripper.repositories.AirportRepository;
import com.devfreaks.tripper.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AirportValidator implements Validator {

    @Autowired
    private AirportRepository repository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Airport.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Airport airport = (Airport) target;

        if (StringUtils.isEmpty(airport.getCode())) {
            errors.rejectValue("code", "airport.code.required");
        } else if (repository.findOne(QAirport.airport.code.eq(airport.getCode())) != null) {
            errors.rejectValue("code", "airport.code.exists");
        }

        if (StringUtils.isEmpty(airport.getName())) {
            errors.rejectValue("name", "airport.name.required");
        }

        if(countryRepository.findOne(airport.getCountry().getId())!= null){
            errors.rejectValue("country_id", "airport.country_id.required");
        }


    }
}