package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.entities.QAirport;
import com.mysema.query.types.path.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirportRepository extends PagingAndSortingRepository<Airport, UUID>,
        QueryDslPredicateExecutor<Airport>, QuerydslBinderCustomizer<QAirport> {

    default void customize(QuerydslBindings bindings, QAirport airport) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringPath::containsIgnoreCase);
    }

}