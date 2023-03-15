package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Reserva")
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "check_in", nullable = false)
    @NotNull(message = "Debe ingresar la fecha de inicio de la reserva")
    @FutureOrPresent(message = "La fecha de inicio no debe ser anterior a la fecha actual")
    private LocalDate checkIn;
    @Column(name = "check_out", nullable = false)
    @NotNull(message = "Debe ingresar la fecha de finalización de la reserva")
    @FutureOrPresent(message = "La fecha de finalización no debe ser anterior a la fecha actual")
    private LocalDate checkOut;
    @Column(name = "hora_llegada", nullable = false)
    @NotNull(message = "Debe ingresar la hora de llegada")
    private LocalTime horaLlegada;
    @Column(name = "ciudad_usuario", nullable = false, length = 100)
    @NotBlank(message = "Debe ingresar la ciudad del usuario")
    @Size(max = 100, message = "La ciudad del usuario no debe tener más de 100 caracteres")
    private String ciudadUsuario;
    @Column(name = "datos_extra", columnDefinition = "TEXT", length = 500)
    @Size(max = 500, message = "Los datos extra al vendedor no deben tener más de 500 caracteres")
    private String datosExtra;
    @Column(name = "vacuna_covid")
    private boolean vacunaCovid;

    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "reserva_usuario_fk")
    )
    @NotNull(message = "Debe ingresar el usuario")
    @JsonIgnoreProperties("reservas")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "reserva_producto_fk")
    )
    @NotNull(message = "Debe ingresar el producto")
    @JsonIgnoreProperties("reservas")
    private Producto producto;

    public Reserva() {
    }

    public Reserva(Long id, LocalDate checkIn, LocalDate checkOut, LocalTime horaLlegada, String datosExtra, boolean vacunaCovid) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.horaLlegada = horaLlegada;
        this.datosExtra = datosExtra;
        this.vacunaCovid = vacunaCovid;
    }

    public Reserva(Long id, LocalDate checkIn, LocalDate checkOut, LocalTime horaLlegada, String datosExtra, boolean vacunaCovid, Producto producto) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCiudadUsuario() {
        return ciudadUsuario;
    }

    public void setCiudadUsuario(String ciudadUsuario) {
        this.ciudadUsuario = ciudadUsuario;
    }
}
