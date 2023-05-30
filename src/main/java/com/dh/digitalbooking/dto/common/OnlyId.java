package com.dh.digitalbooking.dto.common;

import jakarta.validation.constraints.NotNull;

public record OnlyId(
        @NotNull(message = "Id is required")
        Long id
){
}
