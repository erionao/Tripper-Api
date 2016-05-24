package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.services.FlightService;
import com.devfreaks.tripper.validators.FlightValidator;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping(value = "api/search")
@RestController
public class SearchController {

    @Autowired
    private FlightService service;

    @Autowired
    private FlightValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Flight> index(
            @QuerydslPredicate(root = Flight.class) Predicate predicate,
            Pageable pageable) {

        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Flight findOne(@PathVariable UUID id) throws TripperNotFoundException {
        return service.findOne(id);
    }
}
