package com.dh.digitalbooking.dto;

public class UserDetailsDto {
    private Long userId;
    private String userRol;

    public UserDetailsDto() {
    }

    public UserDetailsDto(Long userId, String userRol) {
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
