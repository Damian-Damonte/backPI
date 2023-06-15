package com.dh.digitalbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 60)
    private String title;
    @Column(name = "title_description", length = 100)
    private String titleDescription;
    @Column(name = "description", columnDefinition = "TEXT", length = 1200)
    private String description;
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "price_per_night",precision = 10, scale = 2, nullable = false)
    @NotNull(message = "El product debe tener un precio por noche")
    private BigDecimal pricePerNight;

    @Column(name = "promedio_puntuacion",precision = 3, scale = 1)
    private BigDecimal averageRating;
    @Column(name = "latitude", precision = 17, scale = 15, nullable = false)
    private BigDecimal latitude;
    @Column(name = "longitude", precision = 18, scale = 15, nullable = false)
    private BigDecimal longitude;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_categorias_fk")
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "productos_ciudad_fk")
    )
    private City city;

    @ManyToMany
    @JoinTable(
            name = "products_amenities",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(name = "product_amenity_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "amenity_id",
                    foreignKey = @ForeignKey(name = "amenity_product_id")
            )
    )
    private Set<Amenity> amenities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    @OrderBy("order ASC NULLS LAST ")
    private Set<Image> images = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    private Set<Policy> policies = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "product")
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany(mappedBy = "favorites")
    private Set<User> favorites = new HashSet<>();
}
