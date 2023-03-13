package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Puntuacion")
@Table(name = "puntuaciones")
public class Puntuacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "valor", nullable = false)
    @NotNull(message = "La puntuacion debe tener un valor")
    @Min(value = 1, message = "La puntuacion no puede tener un valor menor a 1")
    @Max(value = 5, message = "La puntuacion no puede tener un valor mayor a 5")
    private Integer valor;

    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "puntuacion_usuario_fk")
    )
    @NotNull(message = "La puntuacion debe estar asociada a un usuario")
    @JsonIgnoreProperties("reservas")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "puntuacion_producto_fk")
    )
    @NotNull(message = "La puntuacion debe estar asociada a un producto")
    @JsonIgnoreProperties("reservas")
    private Producto producto;

    public Puntuacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
