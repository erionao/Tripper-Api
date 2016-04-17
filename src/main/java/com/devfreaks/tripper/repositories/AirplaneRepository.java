package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.Airplane;
import com.devfreaks.tripper.entities.QAirline;
import com.devfreaks.tripper.entities.QAirplane;
import com.mysema.query.types.path.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirplaneRepository extends PagingAndSortingRepository<Airplane, UUID>,
        QueryDslPredicateExecutor<Airplane>, QuerydslBinderCustomizer<QAirplane> {

    default void customize(QuerydslBindings bindings, QAirplane airplane) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringPath::containsIgnoreCase);
    }

}