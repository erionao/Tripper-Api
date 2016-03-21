package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;

import java.util.UUID;

public interface UserService {

    Iterable<User> findAll();

    User findOne(UUID id) throws TripperNotFoundException;

    User save(User user) throws TripperException;

    User update(User user);

    void delete(User user);

}
