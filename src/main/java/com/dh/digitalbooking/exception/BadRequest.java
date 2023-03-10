package com.dh.digitalbooking.exception;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class BadRequest {
    private String message;
    private int status;
    private ZonedDateTime timestamp;

    public BadRequest() {
    }

    public BadRequest(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST.value();
        this.timestamp = ZonedDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
