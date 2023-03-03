package com.proyectoIntegrador.sprint1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "Politica")
@Table(name = "politicas")
public class Politica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT", length = 600)
    private String descripcion;

    @ManyToOne
    @JoinColumn(
            name = "tipo_politica_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "politicas_tipo_politicas_fk")
    )
    private TipoPolitica tipoPolitica;

    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "productos_politicas_id_fk")
    )
    @JsonIgnore
    private Producto producto;

    public Politica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPolitica getTipoPolitica() {
        return tipoPolitica;
    }

    public void setTipoPolitica(TipoPolitica tipoPolitica) {
        this.tipoPolitica = tipoPolitica;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
