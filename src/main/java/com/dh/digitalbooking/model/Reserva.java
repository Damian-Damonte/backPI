package com.dh.digitalbooking.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Reserva")
@Table(name = "Reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;
    @Column(name = "hora_llegada", nullable = false)
    private LocalTime horaLlegada;
    @Column(name = "datos_extra", columnDefinition = "TEXT", length = 500)
    private String datosExtra;
    @Column(name = "vacuna_covid")
    private boolean vacunaCovid;

    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "reserva_producto_fk")
    )
    private Producto producto;
    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "reserva_usuario_fk")
    )
    private Usuario usuario;

    public Reserva() {
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
}
