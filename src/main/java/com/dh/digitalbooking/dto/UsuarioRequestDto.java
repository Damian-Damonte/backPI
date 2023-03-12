package com.dh.digitalbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDto {
    @NotBlank(message = "El usuario debe tener un nombre")
    @Size(max = 45, message = "El nombre no debe tener más de 45 caracteres")
    private String nombre;
    @NotBlank(message = "El usuario debe tener un apellido")
    @Size(max = 45, message = "El apellido no debe tener más de 45 caracteres")
    private String apellido;
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El usuario debe tener un email")
    @Size(max = 255, message = "El email no debe tener más de 255 caracteres")
    private String email;
    @NotBlank(message = "El usuario debe tener un nombre")
    @Size(max = 255, message = "El email no debe tener más de 255 caracteres")
    private String password;

    public UsuarioRequestDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
