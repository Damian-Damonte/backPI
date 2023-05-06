package com.dh.digitalbooking.entity;

import jakarta.persistence.*;
@Entity(name = "Country")
@Table(
        name = "countries",
        uniqueConstraints = {
                @UniqueConstraint(name = "country_name_unique", columnNames = "name")
        }
)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
