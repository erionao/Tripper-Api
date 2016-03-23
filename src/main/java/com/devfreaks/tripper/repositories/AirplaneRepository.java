package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, String> {
}
