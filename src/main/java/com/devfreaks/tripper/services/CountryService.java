package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Country;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CountryService {

    Iterable<Country> findAll(Predicate predicate, Pageable pageable);

    Country findOne(UUID id);

    Country save(Country country);

    Country update(Country country);

    void delete(Country country);

}
