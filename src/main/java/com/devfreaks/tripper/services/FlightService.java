package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FlightService {

    Iterable<Flight> findAll(Predicate predicate, Pageable pageable);

    Flight findOne(UUID id) throws TripperNotFoundException;

    Flight save(Flight flight) throws TripperException;

    Flight update(Flight flight);

    void delete(Flight flight);

}
