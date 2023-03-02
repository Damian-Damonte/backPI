package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "Coordenadas")
@Table(name = "coordenadas")
public class Coordenadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(precision = 10, scale = 6, nullable = false)
    private BigDecimal latitud;
    @Column(precision = 10, scale = 6, nullable = false)
    private BigDecimal longitud;

    public Coordenadas() {
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
}
