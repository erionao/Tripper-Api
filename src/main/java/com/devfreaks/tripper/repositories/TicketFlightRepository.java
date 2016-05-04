package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.*;
import com.mysema.query.types.path.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketFlightRepository extends PagingAndSortingRepository<TicketFlight, UUID>,
        QueryDslPredicateExecutor<Ticket>, QuerydslBinderCustomizer<QTicketFlight> {

    default void customize(QuerydslBindings bindings, QTicketFlight ticketFlight) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringPath::containsIgnoreCase);
    }

}
