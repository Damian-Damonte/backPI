package com.dh.digitalbooking.dto.ciudad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CityPutDTO(
        @NotNull(message = "Debe enviar el id de la ciudad")
        Long id,
        @NotBlank(message = "La ciudad debe tener un nombre")
        @Size(max = 255, message = "El nombre de la ciudad no debe tener más de 255 caracteres")
        String nombre,
        @NotNull(message = "Debe enviar el id del pais")
        Long paisId
) {
}
