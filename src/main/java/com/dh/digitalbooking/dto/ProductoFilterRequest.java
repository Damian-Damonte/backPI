package com.dh.digitalbooking.dto;


import java.time.LocalDate;

public class ProductoFilterRequest {
    private Long ciudadId;
    private Long categoriaId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public ProductoFilterRequest() {
    }

    public ProductoFilterRequest(Long ciudadId, Long categoriaId, LocalDate checkIn, LocalDate checkOut) {
        this.ciudadId = ciudadId;
        this.categoriaId = categoriaId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }
}
