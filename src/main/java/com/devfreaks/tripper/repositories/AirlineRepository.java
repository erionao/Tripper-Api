package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, String> {
}
