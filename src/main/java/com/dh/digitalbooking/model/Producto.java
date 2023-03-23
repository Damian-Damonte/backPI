package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
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
    @NotBlank(message = "El producto debe tener un titulo")
    @Size(max = 45, message = "El titulo no debe tener más de 45 caracteres")
    private String titulo;
    @Column(name = "title_description", length = 100)
    @Size(max = 100, message = "El titulo de la descripcion no debe tener más de 100 caracteres")
    private String tituloDescripcion;
    @Column(name = "descripcion", columnDefinition = "TEXT", length = 1200)
    @Size(max = 1200, message = "La descripcion no debe tener más de 1000 caracteres")
    private String descripcion;
    @Column(name = "direccion", nullable = false)
    @NotBlank(message = "El producto debe tener una dirección")
    @Size(max = 255, message = "La dirección del producto no debe tener más de 255 caracteres")
    private String direccion;

    @Column(name = "precio_por_noche",precision = 10, scale = 2, nullable = false)
    @DecimalMin(value = "0.00",  message = "El precio por noche no puede ser menor a 0.00")
    @DecimalMax(value = "99999999.99", message = "El precio por noche no puede ser mayor a 99999999.99")
    @NotNull(message = "El producto debe tener un precio por noche")
    private BigDecimal precioPorNoche;

    @Column(name = "promedio_puntuacion",precision = 3, scale = 1)
    @DecimalMin(value = "0.00",  message = "El promedio no puede ser menor a 0.00")
    @DecimalMax(value = "10.0", message = "El promedio no puede ser mayor a 10.0")
    private BigDecimal promedioPuntuacion;

    @ManyToOne
    @JoinColumn(
            name = "categoria_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_categorias_fk")
    )
    @NotNull(message = "El producto debe pertenecer a una categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(
            name = "ciudad_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_ciudad_fk")
    )
    @NotNull(message = "El producto debe pertenecer a una ciudad")
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
    @OrderBy("id ASC")
    @Valid
    private Set<Imagen> imagenes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "producto")
    @Valid
    private Set<Politica> politicas = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "coordenadas_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "coordenadas_id_fk")
    )
    @Valid
    private Coordenadas coordenadas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties("producto")
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "producto")
    @JsonIgnore
    private Set<Puntuacion> puntuaciones = new HashSet<>();

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

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public BigDecimal getPromedioPuntuacion() {
        return promedioPuntuacion;
    }

    public void setPromedioPuntuacion(BigDecimal promedio_puntuacion) {
        this.promedioPuntuacion = promedio_puntuacion;
    }
}
