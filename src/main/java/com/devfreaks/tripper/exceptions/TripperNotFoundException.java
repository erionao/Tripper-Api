package com.devfreaks.tripper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TripperNotFoundException extends RuntimeException {

    public TripperNotFoundException(String s) {
        super(s);
    }
}
