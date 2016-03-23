package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/airplanes")
@RestController
public class AirplanesController {

    @Autowired
    private AirplaneService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Airplane> index() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Airplane index(@PathVariable String id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airplane save(@RequestBody @Validated Airplane airplane) {
        return service.save(airplane);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Airplane update(@RequestBody @Validated Airplane model, @PathVariable String id) {
        Airplane airplane = service.findOne(id);

        airplane.setName(model.getName());

        return service.update(airplane);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable String id) {
        service.delete(service.findOne(id));
    }

}
