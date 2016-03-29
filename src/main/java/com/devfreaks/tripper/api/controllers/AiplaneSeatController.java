package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.AirplaneSeat;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.services.AirplaneSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequestMapping(value = "api/airplanes/seats")
@RestController
public class AiplaneSeatController {

    @Autowired
    private AirplaneSeatService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AirplaneSeat> index() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AirplaneSeat index(@PathVariable UUID id) throws TripperNotFoundException {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AirplaneSeat save(@RequestBody @Validated AirplaneSeat seat) throws TripperNotFoundException {
        return service.save(seat);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public AirplaneSeat update(@RequestBody @Validated AirplaneSeat model, @PathVariable UUID id) {
        AirplaneSeat seat = service.findOne(id);
        seat.setSeats(model.getSeats());
        seat.setType(model.getType());
        seat.setAirplane(model.getAirplane());

        return seat;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
