package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "Imagen")
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 50)
    @Size(max = 50, message = "El titulo de la imagen no debe tener más de 50 caracteres")
    @NotBlank(message = "La imagen debe tener un titulo")
    private String titulo;

    @Column(name = "url", nullable = false)
    @NotBlank(message = "La imagen debe tener una url")
    @Size(max = 255, message = "La url de la imagen no debe tener más de 255 caracteres")
    private String url;
    @Column(name = "orden")
    @Min(value = 0, message = "El valor del orden debe ser mayor o igual a 0.")
    @Max(value = 100, message = "El valor del orden debe ser menor o igual a 100.")
    @Nullable
    private Integer orden;

    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "productos_imagenes_id_fk")
    )
    @JsonIgnore
    private Producto producto;

    public Imagen() {
    }

    public Imagen(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public Imagen(String titulo, String url, Integer orden) {
        this.titulo = titulo;
        this.url = url;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
