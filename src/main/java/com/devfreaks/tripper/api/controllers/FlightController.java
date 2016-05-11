package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.FlightService;
import com.devfreaks.tripper.validators.FlightValidator;
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

@RequestMapping(value = "api/flights")
@RestController
public class FlightController {

    @Autowired
    private FlightService service;

    @Autowired
    private FlightValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Flight> index(@QuerydslPredicate(root = Flight.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);
        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Flight findOne(@PathVariable UUID id) throws TripperNotFoundException {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Flight save(@RequestBody Flight flight, BindingResult result) {

        validator.validate(flight, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(flight);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Flight update(@RequestBody @Validated Flight model, @PathVariable UUID id, BindingResult result) {
        Flight flight = service.findOne(id);

        flight.setArrival(model.getArrival());
        flight.setBaggageLimit(model.getBaggageLimit());
        flight.setDeparture(model.getDeparture());
        flight.setFrom(model.getFrom());
        flight.setTo(model.getTo());
        flight.setGate(model.getGate());
        flight.setStatus(model.getStatus());
        flight.setPrice(model.getPrice());
        flight.setCode(model.getCode());

        validator.validate(flight, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.update(flight);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }
}
