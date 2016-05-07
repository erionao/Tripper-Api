package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Flight;
import com.devfreaks.tripper.entities.QFlight;
import com.mysema.query.types.path.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlightRepository extends PagingAndSortingRepository<Flight, UUID>,
        QueryDslPredicateExecutor<Flight>, QuerydslBinderCustomizer<QFlight> {

    default void customize(QuerydslBindings bindings, QFlight flight) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringPath::containsIgnoreCase);
    }

}
