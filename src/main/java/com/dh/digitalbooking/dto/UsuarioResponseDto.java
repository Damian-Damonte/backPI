package com.dh.digitalbooking.dto;

import com.dh.digitalbooking.entity.Booking;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.entity.Rol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String ciudad;
    private Rol rol;
    @JsonIgnoreProperties("usuario")
    private List<Booking> bookings;
    @JsonIgnoreProperties("bookings")
    private List<Producto> favoritos;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(Long id, String nombre, String apellido, String email, String ciudad, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.ciudad = ciudad;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Booking> getReservas() {
        return bookings;
    }

    public void setReservas(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Producto> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Producto> favoritos) {
        this.favoritos = favoritos;
    }
}
