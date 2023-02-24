package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;

@Entity(name = "Ciudad")
@Table(
        name = "ciudades"
)
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(
            name = "pais_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ciudades_paises_fk")
    )
    private Pais pais;

    public Ciudad() {
    }

    public Ciudad(String nombre, Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
