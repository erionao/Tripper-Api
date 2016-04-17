package com.devfreaks.tripper.services;

import com.devfreaks.tripper.entities.Ticket;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TicketService {

    Iterable<Ticket> findAll(Predicate predicate, Pageable pageable);

    Ticket findOne(UUID id);

    Ticket save(Ticket ticket);

    Ticket update(Ticket ticket);

    void delete(Ticket ticket);

}
