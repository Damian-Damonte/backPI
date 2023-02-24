package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;

@Entity(name = "Producto")
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 45)
    private String titulo;
    @Column(name = "title_description", length = 100)
    private String tituloDescripcion;
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(
            name = "categoria_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_categorias_fk")
    )
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String titulo, String tituloDescripcion, String descripcion, Categoria categoria) {
        this.titulo = titulo;
        this.tituloDescripcion = tituloDescripcion;
        this.descripcion = descripcion;
        this.categoria = categoria;
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

    public void setTitulo(String title) {
        this.titulo = title;
    }

    public String getTituloDescripcion() {
        return tituloDescripcion;
    }

    public void setTituloDescripcion(String titleDescription) {
        this.tituloDescripcion = titleDescription;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
