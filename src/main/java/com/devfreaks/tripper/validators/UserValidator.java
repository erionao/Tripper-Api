package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.repositories.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == User.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;

        if (StringUtils.isEmpty(user.getFullName())) {
            errors.rejectValue("fullName", "required", "Full name is required");
        }

        if (StringUtils.isEmpty(user.getLogin())) {
            errors.rejectValue("login", "login.required", "Login is required");
        }

        if (!StringUtils.isEmpty(user.getLogin()) && !EmailValidator.getInstance().isValid(user.getLogin())) {
            errors.rejectValue("login", "login.valid.email", "Login must be a valid email");
        }

        if (user.getId() == null && !StringUtils.isEmpty(user.getLogin()) && repository.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "exists", "Login is taken");
        }

        // When updating the user login for an existing user
        if (user.getId() != null && !repository.findOne(user.getId()).getLogin().equals(user.getLogin()) && repository.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "exists", "Login is taken");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.rejectValue("password", "required", "Password is required");
        }

        if (user.getRole() == null) {
            errors.rejectValue("role", "required", "Role is required");
        }
    }
}
