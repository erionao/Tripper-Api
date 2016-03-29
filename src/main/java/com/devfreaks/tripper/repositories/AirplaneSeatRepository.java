package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.AirplaneSeat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirplaneSeatRepository extends CrudRepository<AirplaneSeat, UUID> {
}
