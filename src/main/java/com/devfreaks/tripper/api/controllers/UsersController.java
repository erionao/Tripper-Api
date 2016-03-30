package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.entities.groups.Save;
import com.devfreaks.tripper.entities.groups.Update;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.UUID;

@RequestMapping(value = "api/users")
@RestController
public class UsersController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User findOne(@PathVariable UUID id) throws TripperNotFoundException {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody @Validated(Save.class) User user) {
        return service.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public User update(@RequestBody @Validated(Update.class) User model, @PathVariable UUID id) throws TripperException {
        User user = service.findOne(id);

        user.setFullName(model.getFullName());
        user.setLogin(model.getLogin());
        user.setPassword(model.getPassword());
        user.setActive(model.getActive());

        return service.update(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) throws TripperNotFoundException {
        service.delete(service.findOne(id));
    }

}
