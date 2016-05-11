package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirportRepository;
import com.devfreaks.tripper.services.AirportService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository repository;


    @Override
    public Iterable<Airport> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Airport findOne(UUID id) {
        Airport airport = repository.findOne(id);

        if (airport == null) {
            throw new TripperNotFoundException("Airport with id '" + id + "' was not found.");
        }

        return airport;
    }

    @Override
    public Airport save(Airport airport) {
        if (airport.getId() != null && repository.findOne(airport.getId()) != null) {
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
