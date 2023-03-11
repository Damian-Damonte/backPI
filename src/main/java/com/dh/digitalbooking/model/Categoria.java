package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Categoria")
@Table(
        name = "categorias",
        uniqueConstraints = {
                @UniqueConstraint(name = "categoria_titulo_unique", columnNames = "titulo")
        }
)
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 45)
    @NotBlank(message = "La categoria debe tener un titulo")
    @Size(max = 45, message = "El titulo no puede tener mas de 45 caracteres")
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    @NotBlank(message = "La categoria debe tener una descripcion")
    @Size(max = 45, message = "El titulo no puede tener mas de 45 caracteres")
    private String descripcion;

    @Column(name = "urlImagen", nullable = false)
    @NotBlank(message = "La categoria debe tener una imagen")
    private String urlImagen;
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    public Categoria() {
    }

    public Categoria(String titulo, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
