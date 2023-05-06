package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Category")
@Table(
        name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "category_name_unique", columnNames = "name")
        }
)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    @NotBlank(message = "La categoria debe tener un titulo")
    @Size(max = 45, message = "El titulo no puede tener mas de 45 caracteres")
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "La categoria debe tener una descripcion")
    @Size(max = 45, message = "El titulo no puede tener mas de 45 caracteres")
    private String description;

    @Column(name = "imageUrl", nullable = false)
    @NotBlank(message = "La categoria debe tener una imagen")
    private String imageUrl;
    @Column(name = "products_count")
    private int productsCount;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Producto> products = new HashSet<>();

    public Category() {
    }

    public Category(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String urlImagen) {
        this.imageUrl = urlImagen;
    }

    public Set<Producto> getProducts() {
        return products;
    }

    public void setProducts(Set<Producto> products) {
        this.products = products;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }
}
