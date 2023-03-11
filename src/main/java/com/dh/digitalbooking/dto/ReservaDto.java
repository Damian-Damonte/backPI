package com.dh.digitalbooking.dto;

import com.dh.digitalbooking.model.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDto {
    private Long id;
    @NotNull(message = "Debe agregar la fecha de inicio de la reserva")
    @FutureOrPresent(message = "La fecha de inicio de la reserva no debe ser anterior a la fecha actual")
    private LocalDate checkIn;
    @NotNull(message = "Debe agregar la fecha de finalización de la reserva")
    @FutureOrPresent(message = "La fecha de finalización de la reserva no debe ser anterior a la fecha actual")
    private LocalDate checkOut;
    @NotNull(message = "Debe agregar la hora de llegada")
    private LocalTime horaLlegada;
    @Size(max = 500, message = "El mensaje extra no debe tener más de 500 caracteres")
    private String datosExtra;
    private boolean vacunaCovid;
    @NotNull(message = "Debe agregar el id del producto")
    @JsonIgnoreProperties("reservas")
    private Producto producto;

    public ReservaDto() {
    }

    public ReservaDto(Long id, LocalDate checkIn, LocalDate checkOut, LocalTime horaLlegada, String datosExtra, boolean vacunaCovid) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.horaLlegada = horaLlegada;
        this.datosExtra = datosExtra;
        this.vacunaCovid = vacunaCovid;
    }

    public ReservaDto(Long id, LocalDate checkIn, LocalDate checkOut, LocalTime horaLlegada, String datosExtra, boolean vacunaCovid, Producto producto) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.horaLlegada = horaLlegada;
        this.datosExtra = datosExtra;
        this.vacunaCovid = vacunaCovid;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getDatosExtra() {
        return datosExtra;
    }

    public void setDatosExtra(String datosExtra) {
        this.datosExtra = datosExtra;
    }

    public boolean isVacunaCovid() {
        return vacunaCovid;
    }

    public void setVacunaCovid(boolean vacunaCovid) {
        this.vacunaCovid = vacunaCovid;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
