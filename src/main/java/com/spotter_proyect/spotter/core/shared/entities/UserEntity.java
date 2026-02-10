package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.shared.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED) // <--- LA CLAVE DE LA HERENCIA
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    // Roles: "ADMIN", "CLIENT", "TRAINER"
    // Usamos String simple para no complicarnos con tablas extra de roles
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
