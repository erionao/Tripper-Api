package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airline;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirlineRepository;
import com.devfreaks.tripper.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository repository;

    @Override
    public Iterable<Airline> findAll() {
        return repository.findAll();
    }

    @Override
    public Airline findOne(String id) throws TripperNotFoundException {
        Airline airline = repository.findOne(id);

        if (airline == null) {
            throw new TripperNotFoundException("Airline with code '" + id + "' was not found.");
        }

        return airline;
    }

    @Override
    public Airline save(Airline airline) throws TripperException {
        if (repository.findOne(airline.getId()) != null) {
            throw new TripperException("Airline with code '" + airline.getId() + "' already exists");
        }

        return repository.save(airline);
    }

    @Override
    public Airline update(Airline airline) {
        return repository.save(airline);
    }

    @Override
    public void delete(Airline airline) {
        repository.delete(airline);
    }
}
