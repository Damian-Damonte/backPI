package com.dh.digitalbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "city")
    private String city;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user")
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(name = "usuario_producto_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(name = "producto_usuario_id")
            )
    )
    private List<Product> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OrderBy("id ASC")
    private Set<Product> products = new HashSet<>();

    public void addFav(Product product) {
        favorites.add(product);
    }
    public void removeFav(Product product) {
        favorites.remove(product);
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }
}
