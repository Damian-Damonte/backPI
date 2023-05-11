package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Rating")
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    @NotNull(message = "Value is required")
    @Min(value = 1, message = "The rating value should not be less than 1")
    @Max(value = 5, message = "The rating value should not greater than 5")
    private Integer value;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "rating_user_fk")
    )
    @NotNull(message = "User id required")
    @JsonIgnoreProperties({"bookings", "favoritos"})
    private Usuario user;
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "rating_product_fk")
    )
    @NotNull(message = "Producto is required")
    @JsonIgnoreProperties("bookings")
    private Producto product;

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer valor) {
        this.value = valor;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario usuario) {
        this.user = usuario;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto producto) {
        this.product = producto;
    }
}
