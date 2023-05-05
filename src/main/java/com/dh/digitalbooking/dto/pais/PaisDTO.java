package com.dh.digitalbooking.dto.pais;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PaisDTO (
        Long id,
        @NotBlank(message = "El pais debe contener un nombre")
        @Size(max = 45, message = "El nombre del pais no debe tener mas de 45 caracteres")
        String nombre){
}
