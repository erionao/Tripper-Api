package com.devfreaks.tripper.exceptions;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ResponseBody
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class TripperValidationException extends RuntimeException {

    public TripperValidationException(List<ObjectError> errors) {
        super(new Gson().toJson(errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
    }

}
