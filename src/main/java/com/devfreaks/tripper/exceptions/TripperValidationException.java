package com.devfreaks.tripper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class TripperValidationException extends RuntimeException {

    public TripperValidationException(List<ObjectError> errors) {
        super(errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()).toString());
    }

}
