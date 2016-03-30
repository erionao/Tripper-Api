package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, UUID> {
}
