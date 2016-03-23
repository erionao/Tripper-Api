package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.AirplaneSeat;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;

public interface AirplaneSeatService {

    Iterable<AirplaneSeat> findAll();

    AirplaneSeat findOne(String id) throws TripperNotFoundException;

    AirplaneSeat save(AirplaneSeat seat) throws TripperException;

    AirplaneSeat update(AirplaneSeat seat) throws TripperException;

    void delete(AirplaneSeat seat);
}
