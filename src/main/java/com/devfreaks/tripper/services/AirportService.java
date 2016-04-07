package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airport;

import java.util.UUID;

public interface AirportService {

    Iterable<Airport> findAll();

    Airport findOne(UUID id);

    Airport save(Airport airport);

    Airport update(Airport airport);

    void delete(Airport airport);

}
