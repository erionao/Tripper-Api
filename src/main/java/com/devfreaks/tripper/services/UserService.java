package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<User> findAll(Predicate predicate, Pageable pageable);

    User findOne(UUID id) throws TripperNotFoundException;

    User findByLogin(String login) throws TripperNotFoundException;

    User save(User user) throws TripperException;

    User update(User user);

    void delete(User user);

}
