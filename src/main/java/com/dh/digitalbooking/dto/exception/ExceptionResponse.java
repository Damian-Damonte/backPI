package com.dh.digitalbooking.dto.exception;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
    private int status;
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private List<String> errors = new ArrayList<>();

    public ExceptionResponse(int status) {
        this.status = status;
    }

    public ExceptionResponse addError(String error) {
        this.errors.add(error);
        return this;
    }
}
