package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.UserRepository;
import com.devfreaks.tripper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findOne(UUID id) throws TripperNotFoundException {
        User user = repository.findOne(id);

        if (user == null) {
            throw new TripperNotFoundException("User with id '" + id + "' was not found.");
        }

        return user;
    }

    @Override
    public User findByLogin(String login) throws TripperNotFoundException {
        User user = repository.findByLogin(login);

        if (user == null) {
            throw new TripperNotFoundException("User with login '" + login + "' was not found.");
        }

        return user;
    }

    @Override
    public User save(User user) throws TripperException {
        if (repository.findByLogin(user.getLogin()) != null) {
            throw new TripperException("User with login '" + user.getLogin() + "' already exists");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);

        return repository.save(user);
    }

    @Override
    public User update(User user) {
        if (!user.getLogin().equals(repository.findOne(user.getId()).getLogin()) && repository.findByLogin(user.getLogin()) != null) {
            throw new TripperException("A user with the same login already exists.");
        }

        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
}
