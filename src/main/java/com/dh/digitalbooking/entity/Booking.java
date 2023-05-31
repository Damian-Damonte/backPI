package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Booking")
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "check_in", nullable = false)
    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "The check-in date cannot be before the current date")
    private LocalDate checkIn;
    @Column(name = "check_out", nullable = false)
    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "The check-out date cannot be before the current date")
    private LocalDate checkOut;
    @Column(name = "hora_llegada", nullable = false)
    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;
    @Column(name = "ciudad_usuario", nullable = false, length = 100)
    @NotBlank(message = "Current user city is required")
    @Size(max = 100, message = "Current user city name cannot be longer than 100 characters")
    private String userCity;
    @Column(name = "datos_extra", columnDefinition = "TEXT", length = 500)
    @Size(max = 500, message = "The additional note cannot be longer than 500 characters")
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
    @NotNull(message = "User is required")
    @JsonIgnoreProperties({"bookings", "favoritos"})
    private User user;
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "booking_product_fk")
    )
    @NotNull(message = "Product is required")
    @JsonIgnoreProperties("bookings")
    private Producto product;

    public Booking() {
    }

    public Booking(Long id, LocalDate checkIn, LocalDate checkOut, LocalTime arrivalTime, String additionalNote, boolean covidVaccine) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.arrivalTime = arrivalTime;
        this.additionalNote = additionalNote;
        this.covidVaccine = covidVaccine;
    }

    public Booking(Long id, LocalDate checkIn, LocalDate checkOut, LocalTime arrivalTime, String additionalNote, boolean covidVaccine, Producto product) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.arrivalTime = arrivalTime;
        this.additionalNote = additionalNote;
        this.covidVaccine = covidVaccine;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public boolean isCovidVaccine() {
        return covidVaccine;
    }

    public void setCovidVaccine(boolean covidVaccine) {
        this.covidVaccine = covidVaccine;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
