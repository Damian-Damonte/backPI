package com.dh.digitalbooking.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthenticateRequest {

    @NotBlank(message = "Debe enviar el email")
    private String email;
    @NotBlank(message = "Debe enviar la contraseña")
    private String password;

    public AuthenticateRequest() {
    }

    public AuthenticateRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
