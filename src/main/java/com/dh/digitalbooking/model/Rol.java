package com.dh.digitalbooking.model;

import jakarta.persistence.Table;

@Table(name = "roles")
public enum Rol {
    ROLE_USER,
    ROLE_ADMIN
}
