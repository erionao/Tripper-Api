package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.CountryService;
import com.devfreaks.tripper.validators.CountryValidator;
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

@RequestMapping(value = "api/countries")
@RestController
public class CountriesController {

    @Autowired
    private CountryService service;

    @Autowired
    private CountryValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Country> index(@QuerydslPredicate(root = Country.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);
        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Country index(@PathVariable UUID id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Country save(@RequestBody @Validated Country country, BindingResult result) {
        validator.validate(country, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(country);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Country update(@RequestBody @Validated Country model, @PathVariable UUID id, BindingResult result) {
        Country country = service.findOne(id);
        country.setName(model.getName());

        validator.validate(country, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.update(country);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
