package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/airports")
@RestController
public class AirportsController {

    @Autowired
    private AirportService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Airport> index() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Airport index(@PathVariable String id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airport save(@RequestBody @Validated Airport airport) {
        return service.save(airport);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Airport update(@RequestBody @Validated Airport model, @PathVariable String id) {
        Airport airport = service.findOne(id);

        airport.setName(model.getName());
        airport.setCountry(model.getCountry());

        return service.update(airport);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable String id) {
        service.delete(service.findOne(id));
    }

}
