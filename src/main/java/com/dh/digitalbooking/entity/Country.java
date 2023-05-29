package com.dh.digitalbooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Country")
@Table(
        name = "countries",
        uniqueConstraints = {
                @UniqueConstraint(name = "country_name_unique", columnNames = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
}
