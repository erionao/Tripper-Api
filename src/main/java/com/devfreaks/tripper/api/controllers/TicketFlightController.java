package com.devfreaks.tripper.api.controllers;


import com.devfreaks.tripper.entities.TicketFlight;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.TicketFlightService;
import com.devfreaks.tripper.validators.TicketFlightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "api/tickets/flights")
@RestController
public class TicketFlightController {

    @Autowired
    private TicketFlightService service;

    @Autowired
    private TicketFlightValidator validator;

    @RequestMapping(method = RequestMethod.POST)
    public TicketFlight save(@RequestBody TicketFlight ticketFlight, BindingResult result) {
        validator.validate(ticketFlight, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(ticketFlight);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
