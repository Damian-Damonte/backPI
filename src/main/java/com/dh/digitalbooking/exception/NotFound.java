package com.dh.digitalbooking.exception;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class NotFound {
    private String message;
    private int status;
    private ZonedDateTime timestamp;

    public NotFound() {
    }

    public NotFound(String message) {
        this.message = message;
        this.status = HttpStatus.NOT_FOUND.value();
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
