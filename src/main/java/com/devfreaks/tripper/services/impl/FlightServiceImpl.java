package com.devfreaks.tripper.services.impl;


import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.FlightRepository;
import com.devfreaks.tripper.services.FlightService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository repository;

    @Override
    public Iterable<Flight> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Flight findOne(UUID id) {
        Flight flight = repository.findOne(id);

        if (flight == null) {
            throw new TripperNotFoundException("Flight with id '" + id + "' was not found.");
        }

        return flight;
    }

    @Override
    public Flight save(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public Flight update(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public void delete(Flight flight) {
        repository.delete(flight);
    }
}
