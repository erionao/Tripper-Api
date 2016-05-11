package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.CountryRepository;
import com.devfreaks.tripper.services.CountryService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public Iterable<Country> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Country findOne(UUID id) {
        Country country = repository.findOne(id);

        if (country == null) {
            throw new TripperNotFoundException("Country with id '" + id + "' was not found.");
        }

        return country;
    }

    @Override
    public Country save(Country country) {
        return repository.save(country);
    }

    @Override
    public Country update(Country country) {
        return repository.save(country);
    }

    @Override
    public void delete(Country country) {
        repository.delete(country);
    }
}
