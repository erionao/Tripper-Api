package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
}
