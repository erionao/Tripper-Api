package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirportRepository extends CrudRepository<Airport, UUID> {
}
