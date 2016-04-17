package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.AirplaneService;
import com.devfreaks.tripper.validators.AirplaneValidator;
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

@RequestMapping(value = "api/airplanes")
@RestController
public class AirplanesController {

    @Autowired
    private AirplaneService service;

    @Autowired
    private AirplaneValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Airplane> index(@QuerydslPredicate(root = Airplane.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);
        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Airplane index(@PathVariable UUID id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airplane save(@RequestBody @Validated Airplane airplane, BindingResult result) {
        validator.validate(airplane, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(airplane);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Airplane update(@RequestBody @Validated Airplane model, @PathVariable UUID id, BindingResult result) {
        Airplane airplane = service.findOne(id);
        airplane.setName(model.getName());

        validator.validate(airplane, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.update(airplane);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
