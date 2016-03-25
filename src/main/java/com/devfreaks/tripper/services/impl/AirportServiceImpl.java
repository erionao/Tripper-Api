package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirportRepository;
import com.devfreaks.tripper.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository repository;


    @Override
    public Iterable<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    public Airport findOne(String id) {
        Airport airport = repository.findOne(id);

        if (airport == null) {
            throw new TripperNotFoundException("Airport with id '" + id + "' was not found.");
        }

        return airport;
    }

    @Override
    public Airport save(Airport airport) {
        if (repository.findOne(airport.getId()) != null) {
            throw new TripperException("Airport with id '" + airport.getId() + "' already exists.");
        }

        return repository.save(airport);
    }

    @Override
    public Airport update(Airport airport) {
        return repository.save(airport);
    }

    @Override
    public void delete(Airport airport) {
        repository.delete(airport);
    }
}
