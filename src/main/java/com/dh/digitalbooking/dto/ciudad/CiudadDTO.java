package com.dh.digitalbooking.dto.ciudad;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CiudadDTO(
        @NotNull(message = "Debe enviar el id de la ciudad")
        Long id,
        @NotBlank(message = "La ciudad debe tener un nombre")
        @Size(max = 255, message = "El nombre de la ciudad no debe tener más de 255 caracteres")
        String nombre,
        @NotNull(message = "La ciudad debe pertenecer a un país") @Valid
        PaisDTO pais
) {
}
