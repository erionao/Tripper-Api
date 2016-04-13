package com.devfreaks.tripper.validators;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.repositories.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            errors.rejectValue("fullName", "required", "user.fullName.required");
        }

        if (StringUtils.isEmpty(user.getLogin())) {
            errors.rejectValue("login", "required", "user.login.required");
        }

        if (!EmailValidator.getInstance().isValid(user.getLogin())) {
            errors.rejectValue("login", "valid.email", "user.login.valid.email");
        }

        if (user.getId() == null && !StringUtils.isEmpty(user.getLogin()) && repository.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "exists", "user.login.exists");
        }

        if (user.getId() != null && !repository.findOne(user.getId()).getLogin().equals(user.getLogin()) && repository.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "exists", "user.login.exists");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.rejectValue("password", "required", "user.password.required");
        }

        if (user.getRole() == null) {
            errors.rejectValue("role", "required", "user.role.required");
        }
    }
}
