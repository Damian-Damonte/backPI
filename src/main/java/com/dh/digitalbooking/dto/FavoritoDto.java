package com.dh.digitalbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FavoritoDto {
    @NotNull(message = "Debe enviar el id del usuario")
    private Long usuarioId;
    @NotNull(message = "Debe enviar el id del producto")
    private Long productoId;

    public FavoritoDto() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}
