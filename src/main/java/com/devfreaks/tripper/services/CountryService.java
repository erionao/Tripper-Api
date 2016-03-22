package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Country;
import org.springframework.stereotype.Service;

public interface CountryService {

    Iterable<Country> findAll();

    Country findOne(String id);

    Country save(Country country);

    Country update(Country country);

    void delete(Country country);

}
