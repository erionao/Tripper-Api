package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRepository extends CrudRepository<Country, UUID> {
}
