package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Politica")
@Table(name = "politicas")
public class Politica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT", length = 600)
    @Size(max = 600, message = "La descripcion de la política no debe tener más de 600 caracteres")
    @NotBlank(message = "La política debe tener una descrpción")
    private String descripcion;

    @ManyToOne
    @JoinColumn(
            name = "tipo_politica_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "politicas_tipo_politicas_fk")
    )
    @NotNull(message = "La polílica debe tener un tipo de política")
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

    public Politica(String descripcion, TipoPolitica tipoPolitica) {
        this.descripcion = descripcion;
        this.tipoPolitica = tipoPolitica;
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
