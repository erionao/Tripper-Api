package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Airline;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "api/airlines")
@RestController
public class AirlinesController {

    @Autowired
    private AirlineService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Airline> index() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Airline index(@PathVariable UUID id) throws TripperNotFoundException {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airline save(@RequestBody @Validated Airline airline) {
        return service.save(airline);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Airline update(@RequestBody @Validated Airline model, @PathVariable UUID id) throws TripperNotFoundException {
        Airline airline = service.findOne(id);

        airline.setName(model.getName());

        return service.save(airline);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) throws TripperNotFoundException {
        service.delete(service.findOne(id));
    }

}
