package com.dh.digitalbooking.entity;

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
    @Size(max = 1200, message = "La descripcion no debe tener más de 1200 caracteres")
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
    @Column(name = "latitude", precision = 17, scale = 15, nullable = false)
    @NotNull(message = "Debe agregar la latitud de las coordenadas del producto")
    @DecimalMin(value = "-90.0",  message = "La latitud no debe ser menor a -90.0")
    @DecimalMax(value = "90.0", message = "La latitud no debe ser mayor a 90.0")
    private BigDecimal latitude;
    @Column(name = "longitude", precision = 18, scale = 15, nullable = false)
    @NotNull(message = "Debe agregar la longitud de las coordenadas del producto")
    @DecimalMin(value = "-180.0",  message = "La longitud no debe ser menor a -180.0")
    @DecimalMax(value = "180.0", message = "La latitud no debe ser mayor a 180.0")
    private BigDecimal longitude;

    @ManyToOne
    @JoinColumn(
            name = "categoria_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_categorias_fk")
    )
    @NotNull(message = "El producto debe pertenecer a una category")
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "ciudad_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_ciudad_fk")
    )
    @NotNull(message = "El producto debe pertenecer a una city")
    private City city;

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
    private Set<Amenity> amenities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "producto")
    @OrderBy("order ASC NULLS LAST ")
    @Valid
    @Size(max = 50, message = "El producto no puede tener más de 50 imágenes")
    private Set<Image> imagenes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    @Valid
    private Set<Policy> policies = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties("producto")
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "producto")
    @JsonIgnore
    private Set<Puntuacion> puntuaciones = new HashSet<>();

    @ManyToMany(mappedBy = "favoritos")
    @JsonIgnore
    private Set<Usuario> favoritos = new HashSet<>();

    public Producto() {
    }

    public Producto(String titulo, String tituloDescripcion, String descripcion, Category category, City city) {
        this.titulo = titulo;
        this.tituloDescripcion = tituloDescripcion;
        this.descripcion = descripcion;
        this.category = category;
        this.city = city;
    }

    public Producto(String titulo, String tituloDescripcion, String descripcion, Category category, City city, Set<Amenity> amenities, Set<Image> imagenes, Set<Policy> policies) {
        this.titulo = titulo;
        this.tituloDescripcion = tituloDescripcion;
        this.descripcion = descripcion;
        this.category = category;
        this.city = city;
        this.amenities = amenities;
        this.imagenes = imagenes;
        this.policies = policies;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Category getCategoria() {
        return category;
    }

    public void setCategoria(Category category) {
        this.category = category;
    }

    public City getCiudad() {
        return city;
    }

    public void setCiudad(City city) {
        this.city = city;
    }

    public Set<Amenity> getCaracteristicas() {
        return amenities;
    }

    public void setCaracteristicas(Set<Amenity> amenities) {
        this.amenities = amenities;
    }

    public Set<Image> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Image> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<Policy> getPoliticas() {
        return policies;
    }

    public void setPoliticas(Set<Policy> policies) {
        this.policies = policies;
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

    public Set<Usuario> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Usuario> favoritos) {
        this.favoritos = favoritos;
    }
}
