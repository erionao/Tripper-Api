package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.entities.enums.UserRole;
import com.devfreaks.tripper.entities.groups.Save;
import com.devfreaks.tripper.entities.groups.Update;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.UserService;
import com.devfreaks.tripper.validators.UserValidator;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "api/users")
@RestController
public class UsersController {

    @Autowired
    private UserService service;

    @Autowired
    private UserValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> findAll(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);
        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User findOne(@PathVariable UUID id) throws TripperNotFoundException {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user, BindingResult result) {
        user.setRole(UserRole.ADMINISTRATOR);
        validator.validate(user, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public User update(@RequestBody @Validated(Update.class) User model, @PathVariable UUID id) throws TripperException {
        User user = service.findOne(id);

        user.setFullName(model.getFullName());
        user.setLogin(model.getLogin());
        user.setActive(model.getActive());

        if (model.getPassword() != null && !model.getPassword().trim().equals("")) {
            user.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        }

        return service.update(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) throws TripperNotFoundException {
        service.delete(service.findOne(id));
    }

}
