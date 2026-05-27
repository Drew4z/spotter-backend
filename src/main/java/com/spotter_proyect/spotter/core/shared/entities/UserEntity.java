package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.shared.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED) // <--- LA CLAVE DE LA HERENCIA
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Roles: "ADMIN", "CLIENT", "TRAINER"
    // Usamos String simple para no complicarnos con tablas extra de roles
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(columnDefinition = "TEXT")
    private String pathAvatar;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isPremium = false;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isBanned = false;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    // =============================================================
    // MÉTODOS DE SPRING SECURITY (UserDetails)
    // =============================================================

    // 2. ¿Qué roles tiene este usuario?
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertimos tu String "role" en un objeto que Spring entienda
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    // 3. ¿Cuál es la contraseña?
    @Override
    public String getPassword() {
        return password;
    }

    // 4. ¿Cuál es el "nombre de usuario" para loguearse?
    // ¡IMPORTANTE! Aquí devolvemos el EMAIL, porque es con lo que hacen login.
    @Override
    public String getUsername() {
        return email;
    }

    // 5. Métodos de estado de cuenta (Booleans)
    // Para este proyecto, devuelve siempre 'true' (cuenta activa, no expirada, etc.)
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
}

