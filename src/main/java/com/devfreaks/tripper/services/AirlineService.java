package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airline;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;

public interface AirlineService {

    Iterable<Airline> findAll();

    Airline findOne(String id) throws TripperNotFoundException;

    Airline save(Airline airline) throws TripperException;

    Airline update(Airline airline);

    void delete(Airline airline);

}
