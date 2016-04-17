package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.Ticket;
import com.devfreaks.tripper.exceptions.TripperValidationException;
import com.devfreaks.tripper.services.TicketService;
import com.devfreaks.tripper.validators.TicketValidator;
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

@RequestMapping(value = "api/tickets")
@RestController
public class TicketsController {

    @Autowired
    private TicketService service;

    @Autowired
    private TicketValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Ticket> index(@QuerydslPredicate(root = Ticket.class) Predicate predicate, Pageable pageable, @RequestParam("sortOrder") String sortOrder, @RequestParam("sort") String sort) {
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortOrder.toUpperCase()), sort);
        return service.findAll(predicate, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Ticket index(@PathVariable UUID id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Ticket save(@RequestBody @Validated Ticket ticket, BindingResult result) {
        validator.validate(ticket, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.save(ticket);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Ticket update(@RequestBody @Validated Ticket model, @PathVariable UUID id, BindingResult result) {
        Ticket ticket = service.findOne(id);

        ticket.setCode(model.getCode());
        ticket.setBirthday(model.getBirthday());
        ticket.setFullName(model.getFullName());
        ticket.setPassportNo(model.getPassportNo());
        ticket.setUser(model.getUser());

        validator.validate(ticket, result);

        if (result.hasErrors()) {
            throw new TripperValidationException(result.getAllErrors());
        }

        return service.update(ticket);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.findOne(id));
    }

}
