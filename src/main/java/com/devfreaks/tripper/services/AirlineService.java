package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airline;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AirlineService {

    Iterable<Airline> findAll(Predicate predicate, Pageable pageable);

    Airline findOne(UUID id);

    Airline save(Airline airline);

    Airline update(Airline airline);

    void delete(Airline airline);

}
