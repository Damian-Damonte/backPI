package com.dh.digitalbooking.exception;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class BadRequest {
    private int status = HttpStatus.BAD_REQUEST.value();
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private List<String> errors = new ArrayList<>();

    public BadRequest() {
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }


    public BadRequest addError(String error) {
        this.errors.add(error);
        return this;
    }
}
