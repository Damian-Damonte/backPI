package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity(name = "Coordinates")
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(precision = 17, scale = 15, nullable = false)
    @NotNull(message = "Las coordenadas debe tener una latitud")
    @DecimalMin(value = "-90.0",  message = "La latitud no debe ser menor a -90.0")
    @DecimalMax(value = "90.0", message = "La latitud no debe ser mayor a 90.0")
    private BigDecimal latitude;
    @Column(precision = 18, scale = 15, nullable = false)
    @NotNull(message = "Las coordenadas debe tener una longitud")
    @DecimalMin(value = "-180.0",  message = "La longitud no debe ser menor a -180.0")
    @DecimalMax(value = "180.0", message = "La latitud no debe ser mayor a 180.0")
    private BigDecimal longitude;

    @OneToOne(mappedBy = "coordinates")
    @JsonIgnore
    private Producto product;

    public Coordinates() {
    }

    public Coordinates(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }
}
