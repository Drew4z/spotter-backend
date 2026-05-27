package com.spotter_proyect.spotter.core.shared.model;


import com.spotter_proyect.spotter.core.shared.enums.Roles;

import java.time.LocalDateTime;

public interface User {
    // Definimos los métodos que SI O SI deben tener los hijos.
    // Al ser records, estos métodos coinciden con los nombres de los campos.
    Long id();
    String name();
    String email();
    String password();
    Roles role();
    String pathAvatar();
    Boolean isPremium();
    LocalDateTime createdAt();
}
