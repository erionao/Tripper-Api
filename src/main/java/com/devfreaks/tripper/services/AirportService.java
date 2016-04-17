package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airport;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AirportService {

    Iterable<Airport> findAll(Predicate predicate, Pageable pageable);

    Airport findOne(UUID id);

    Airport save(Airport airport);

    Airport update(Airport airport);

    void delete(Airport airport);

}
