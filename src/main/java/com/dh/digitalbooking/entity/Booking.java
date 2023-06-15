package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Booking")
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;
    @Column(name = "hora_llegada", nullable = false)
    private LocalTime arrivalTime;
    @Column(name = "ciudad_usuario", nullable = false, length = 100)
    private String userCity;
    @Column(name = "datos_extra", columnDefinition = "TEXT", length = 500)
    private String additionalNote;
    @Column(name = "vacuna_covid")
    private boolean covidVaccine;
    @Column(name = "total", precision = 14, scale = 2, nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "booking_user_fk")
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "booking_product_fk")
    )
    private Product product;
}
