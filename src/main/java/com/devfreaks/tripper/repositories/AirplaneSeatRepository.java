package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.AirplaneSeat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneSeatRepository extends CrudRepository<AirplaneSeat, String> {
}
