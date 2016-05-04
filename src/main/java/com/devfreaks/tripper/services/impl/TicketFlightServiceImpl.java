package com.devfreaks.tripper.services.impl;


import com.devfreaks.tripper.entities.TicketFlight;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.TicketFlightRepository;
import com.devfreaks.tripper.services.TicketFlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TicketFlightServiceImpl implements TicketFlightService{

    @Autowired
    private TicketFlightRepository repository;

    @Override
    public TicketFlight findOne(UUID id) {
        TicketFlight ticketFlight = repository.findOne(id);

        if (ticketFlight == null) {
            throw new TripperNotFoundException("Ticket Flight with id '" + id + "' was not found.");
        }

        return ticketFlight;
    }

    @Override
    public TicketFlight save(TicketFlight ticketFlight){
        return repository.save(ticketFlight);
    }

    @Override
    public void delete(TicketFlight ticketFlight){
        repository.delete(ticketFlight);
    }




}
