package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirplaneRepository;
import com.devfreaks.tripper.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository repository;

    @Override
    public Iterable<Airplane> findAll() {
        return repository.findAll();
    }

    @Override
    public Airplane findOne(String id) {
        Airplane airplane = repository.findOne(id);

        if (airplane == null) {
            throw new TripperNotFoundException("Airplane with id '" + id + "' was not found.");
        }

        return airplane;
    }

    @Override
    public Airplane save(Airplane airplane) {
        return repository.save(airplane);
    }

    @Override
    public Airplane update(Airplane airplane) {
        return repository.save(airplane);
    }

    @Override
    public void delete(Airplane airplane) {
        repository.delete(airplane);
    }
}
