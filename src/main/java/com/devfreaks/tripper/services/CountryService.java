package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Country;

import java.util.UUID;

public interface CountryService {

    Iterable<Country> findAll();

    Country findOne(UUID id);

    Country save(Country country);

    Country update(Country country);

    void delete(Country country);

}
