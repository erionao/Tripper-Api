package com.devfreaks.tripper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TripperException extends RuntimeException {

    public TripperException(String s) {
        super(s);
    }

}
