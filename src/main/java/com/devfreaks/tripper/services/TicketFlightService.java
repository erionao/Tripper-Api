package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.TicketFlight;

import java.util.UUID;

public interface TicketFlightService {

    TicketFlight findOne(UUID id);

    TicketFlight save(TicketFlight ticketFlight);

    void delete(TicketFlight ticketFlight);
}
