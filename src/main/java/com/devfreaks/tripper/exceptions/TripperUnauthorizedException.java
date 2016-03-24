package com.devfreaks.tripper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class TripperUnauthorizedException extends RuntimeException {

    public TripperUnauthorizedException(String s) {
        super(s);
    }

}
