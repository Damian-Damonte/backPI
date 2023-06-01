package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Amenity")
@Table(
        name = "amenities",
        uniqueConstraints = {
                @UniqueConstraint(name = "amenities_name_unique", columnNames = "name")
        }
)
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @ManyToMany(mappedBy = "amenities")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}
