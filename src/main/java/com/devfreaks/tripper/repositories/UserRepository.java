package com.devfreaks.tripper.repositories;

import com.devfreaks.tripper.entities.QUser;
import com.devfreaks.tripper.entities.User;
import com.mysema.query.types.path.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID>,
        QueryDslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

    User findByLogin(String login);

    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringPath::containsIgnoreCase);
    }

}
