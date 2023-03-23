package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity(name = "Coordenadas")
@Table(name = "coordenadas")
public class Coordenadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(precision = 17, scale = 15, nullable = false)
    @NotNull(message = "Las coordenadas debe tener una latitud")
    @DecimalMin(value = "-90.000000000000000",  message = "La latitud no debe ser menor a -90.000000000000000")
    @DecimalMax(value = "90.000000000000000", message = "La latitud no debe ser mayor a 90.000000000000000")
    private BigDecimal latitud;
    @Column(precision = 18, scale = 15, nullable = false)
    @NotNull(message = "Las coordenadas debe tener una longitud")
    @DecimalMin(value = "-180.000000000000000",  message = "La longitud no debe ser menor a -180.000000000000000")
    @DecimalMax(value = "180.000000000000000", message = "La latitud no debe ser mayor a 180.000000000000000")
    private BigDecimal longitud;

    @OneToOne(mappedBy = "coordenadas")
    @JsonIgnore
    private Producto producto;

    public Coordenadas() {
    }

    public Coordenadas(BigDecimal latitud, BigDecimal longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
