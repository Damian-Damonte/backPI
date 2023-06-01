package com.dh.digitalbooking.dto.product;

import com.dh.digitalbooking.dto.common.OnlyId;
import com.dh.digitalbooking.dto.image.ImageRequest;
import com.dh.digitalbooking.dto.policy.PolicyRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        @NotBlank(message = "El product debe tener un titulo")
        @Size(max = 45, message = "El titulo no debe tener más de 45 caracteres")
        String title,
        @NotBlank(message = "El product debe tener un titulo de la descripcion")
        @Size(max = 100, message = "El titulo de la descripcion no debe tener más de 100 caracteres")
        String titleDescription,
        @NotBlank(message = "El product debe tener una descripcion")
        @Size(max = 1200, message = "La descripcion no debe tener más de 1200 caracteres")
        String description,
        @NotBlank(message = "El product debe tener una dirección")
        @Size(max = 255, message = "La dirección del product no debe tener más de 255 caracteres")
        String address,
        @DecimalMin(value = "0.00",  message = "El precio por noche no puede ser menor a 0.00")
        @DecimalMax(value = "99999999.99", message = "El precio por noche no puede ser mayor a 99999999.99")
        @NotNull(message = "El product debe tener un precio por noche")
        BigDecimal pricePerNight,
        @NotNull(message = "Debe agregar la latitud de las coordenadas del product")
        @DecimalMin(value = "-90.0",  message = "La latitud no debe ser menor a -90.0")
        @DecimalMax(value = "90.0", message = "La latitud no debe ser mayor a 90.0")
        BigDecimal latitude,
        @NotNull(message = "Debe agregar la longitud de las coordenadas del product")
        @DecimalMin(value = "-180.0",  message = "La longitud no debe ser menor a -180.0")
        @DecimalMax(value = "180.0", message = "La latitud no debe ser mayor a 180.0")
        BigDecimal longitude,
        @NotNull(message = "El product debe pertenecer a una category")
        @Valid
        OnlyId category,
        @NotNull(message = "El product debe pertenecer a una city")
        @Valid
        OnlyId city,
        @Valid
        List<OnlyId> amenities,
        @Size(max = 50, min = 1,message = "El product no puede tener de 1 a 50 imagenes")
        @Valid
        List<ImageRequest> images,
        @Valid
        List<PolicyRequest> policies
) {
}
