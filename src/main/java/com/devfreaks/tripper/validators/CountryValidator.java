package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.entities.QCountry;
import com.devfreaks.tripper.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryValidator implements Validator {

    @Autowired
    private CountryRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Country.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Country country = (Country) target;

        if (StringUtils.isEmpty(country.getCode())) {
            errors.rejectValue("code", "code.required");
        } else if (country.getId() != null
                && repository.findOne(country.getId()) == null
                && repository.findOne(QCountry.country.code.eq(country.getCode())) != null) {
            errors.rejectValue("code", "code.exists");
        }

        if (StringUtils.isEmpty(country.getName())) {
            errors.rejectValue("name", "name.required");
        }
    }
}
