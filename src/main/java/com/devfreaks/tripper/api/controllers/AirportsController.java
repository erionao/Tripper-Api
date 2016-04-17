package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.AirportService;
import com.devfreaks.tripper.validators.AirportValidator;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "api/airports")
@RestController
public class AirportsController {

    @Autowired
    private AirportService service;

    @Autowired
    private AirportValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Airport> index(@QuerydslPredicate(root = Airport.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);
        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Airport index(@PathVariable UUID id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airport save(@RequestBody @Validated Airport airport, BindingResult result) {
        validator.validate(airport, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(airport);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Airport update(@RequestBody @Validated Airport model, @PathVariable UUID id, BindingResult result) {
        Airport airport = service.findOne(id);

        airport.setName(model.getName());
        airport.setCountry(model.getCountry());
        validator.validate(airport, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.update(airport);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
