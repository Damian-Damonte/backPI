package com.dh.digitalbooking.dto;

public class AuthenticationDto {
    private Long userId;
    private String userRol;

    public AuthenticationDto() {
    }

    public AuthenticationDto(Long userId, String userRol) {
        this.userId = userId;
        this.userRol = userRol;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRol() {
        return userRol;
    }

    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }
}
