package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(
            name = "ciudad_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_ciudad_fk")
    )
    private Ciudad ciudad;

    @ManyToMany
    @JoinTable(
            name = "productos_caracteristicas",
            joinColumns = @JoinColumn(
                    name = "producto_id",
                    foreignKey = @ForeignKey(name = "producto_caracteristica_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "caracteristica_id",
                    foreignKey = @ForeignKey(name = "caracteristica_producto_id")
            )
    )
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "producto")
    private Set<Imagen> imagenes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "producto")
    private Set<Politica> politicas = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "coordenadas_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "coordenadas_id_fk")
    )
    private Coordenadas coordenadas;

    public Producto() {
    }

    public Producto(String titulo, String tituloDescripcion, String descripcion, Categoria categoria, Ciudad ciudad) {
        this.titulo = titulo;
        this.tituloDescripcion = tituloDescripcion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ciudad = ciudad;
    }

    public Producto(String titulo, String tituloDescripcion, String descripcion, Categoria categoria, Ciudad ciudad, Set<Caracteristica> caracteristicas, Set<Imagen> imagenes, Set<Politica> politicas, Coordenadas coordenadas) {
        this.titulo = titulo;
        this.tituloDescripcion = tituloDescripcion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.caracteristicas = caracteristicas;
        this.imagenes = imagenes;
        this.politicas = politicas;
        this.coordenadas = coordenadas;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<Politica> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(Set<Politica> politicas) {
        this.politicas = politicas;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }
}
