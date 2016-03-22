package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/countries")
@RestController
public class CountriesController {

    @Autowired
    private CountryService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Country> index() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Country index(@PathVariable String id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Country save(@RequestBody @Validated Country country) {
        return service.save(country);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Country update(@RequestBody @Validated Country model, @PathVariable String id) {
        Country country = service.findOne(id);
        country.setName(model.getName());

        return service.update(country);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable String id) {
        service.delete(service.findOne(id));
    }

}
