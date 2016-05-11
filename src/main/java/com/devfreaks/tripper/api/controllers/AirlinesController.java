package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Airline;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.AirlineService;
import com.devfreaks.tripper.validators.AirlineValidator;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "api/airlines")
@RestController
public class AirlinesController {

    @Autowired
    private AirlineService service;

    @Autowired
    private AirlineValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Airline> index(@QuerydslPredicate(root = Airline.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);

        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Airline index(@PathVariable UUID id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airline save(@RequestBody Airline airline, BindingResult result) {
        validator.validate(airline, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(airline);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Airline update(@RequestBody Airline model, @PathVariable UUID id, BindingResult result) {
        Airline airline = service.findOne(id);
        airline.setName(model.getName());

        validator.validate(airline, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.update(airline);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
