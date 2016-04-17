package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.AirplaneRepository;
import com.devfreaks.tripper.services.AirplaneService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository repository;

    @Override
    public Iterable<Airplane> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Airplane findOne(UUID id) {
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
