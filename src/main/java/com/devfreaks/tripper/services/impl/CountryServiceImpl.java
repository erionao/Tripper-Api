package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.CountryRepository;
import com.devfreaks.tripper.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public Iterable<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Country findOne(String id) {
        Country country = repository.findOne(id);

        if (country == null) {
            throw new TripperNotFoundException("Country with id '" + id + "' was not found.");
        }

        return country;
    }

    @Override
    public Country save(Country country) {
        if (repository.findOne(country.getId()) != null) {
            throw new TripperException("A country with the same id already exists");
        }

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
