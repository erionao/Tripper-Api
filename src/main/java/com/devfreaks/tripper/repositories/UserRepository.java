package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByLogin(String login);

}
