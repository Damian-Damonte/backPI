package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;

@Entity(name = "Producto")
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", length = 45)
    private String title;
    @Column(name = "title_description", length = 100)
    private String titleDescription;
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String description;

    public Producto() {
    }

    public Producto(String title, String titleDescription, String description) {
        this.title = title;
        this.titleDescription = titleDescription;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
