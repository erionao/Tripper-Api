package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airport;

public interface AirportService {

    Iterable<Airport> findAll();

    Airport findOne(String id);

    Airport save(Airport airport);

    Airport update(Airport airport);

    void delete(Airport airport);

}
