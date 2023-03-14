package com.dh.digitalbooking.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public class ProductoFilterRequest {
    private Long ciudadId;
    private Long categoriaId;
    @FutureOrPresent(message = "La fecha de inicio no debe ser anterior a la fecha actual")
    private LocalDate checkIn;
    @FutureOrPresent(message = "La fecha de finalización no debe ser anterior a la fecha actual")
    private LocalDate checkOut;

    public ProductoFilterRequest() {
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
