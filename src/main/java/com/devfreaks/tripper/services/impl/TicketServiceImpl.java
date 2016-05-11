package com.devfreaks.tripper.services.impl;

import com.devfreaks.tripper.entities.Ticket;
import com.devfreaks.tripper.exceptions.TripperNotFoundException;
import com.devfreaks.tripper.repositories.TicketRepository;
import com.devfreaks.tripper.services.TicketService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repository;

    @Override
    public Iterable<Ticket> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Ticket findOne(UUID id) {
        Ticket ticket = repository.findOne(id);

        if (ticket == null) {
            throw new TripperNotFoundException("Ticket with id '" + id + "' was not found.");
        }

        return ticket;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        repository.delete(ticket);
    }
}
