package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airplane;
import org.springframework.data.domain.Pageable;
import com.mysema.query.types.Predicate;

import java.util.UUID;

public interface AirplaneService {

    Iterable<Airplane> findAll(Predicate predicate, Pageable pageable);

    Airplane findOne(UUID id);

    Airplane save(Airplane airplane);

    Airplane update(Airplane airplane);

    void delete(Airplane airplane);

}
