package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirplaneSeatRepository;
import com.devfreaks.tripper.services.AirplaneSeatService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devfreaks.tripper.entities.AirplaneSeat;

@Service
public class AirplaneSeatServiceImpl implements AirplaneSeatService {

    @Autowired
    private AirplaneSeatRepository repository;

    @Override
    public Iterable<AirplaneSeat> findAll() {
        return repository.findAll();
    }

    @Override
    public AirplaneSeat findOne(java.lang.String id) {
        AirplaneSeat seat = repository.findOne(id);

        if (seat == null) {
            throw new TripperNotFoundException("Airplane with id '" + id + "' was not found.");
        }

        return seat;
    }

    @Override
    public AirplaneSeat save(AirplaneSeat seat) {
        return repository.save(seat);
    }

    @Override
    public AirplaneSeat update(AirplaneSeat seat) {
        return repository.save(seat);
    }

    @Override
    public void delete(AirplaneSeat seat) {
        repository.delete(seat);
    }
}

