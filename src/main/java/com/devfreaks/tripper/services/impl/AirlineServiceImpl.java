package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airline;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirlineRepository;
import com.devfreaks.tripper.services.AirlineService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository repository;

    @Override
    public Iterable<Airline> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Airline findOne(UUID id) {
        Airline airline = repository.findOne(id);

        if (airline == null) {
            throw new TripperNotFoundException("Airline with id '" + id + "' was not found.");
        }

        return airline;
    }

    @Override
    public Airline save(Airline airline) {
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
