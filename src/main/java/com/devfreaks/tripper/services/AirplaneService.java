package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Airplane;

public interface AirplaneService {

    Iterable<Airplane> findAll();

    Airplane findOne(String id);

    Airplane save(Airplane airplane);

    Airplane update(Airplane airplane);

    void delete(Airplane airplane);

}
