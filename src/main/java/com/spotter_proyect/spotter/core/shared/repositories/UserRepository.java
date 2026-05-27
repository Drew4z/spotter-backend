package com.spotter_proyect.spotter.core.shared.repositories;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // ¡Magia! Spring implementa esto solo con leer el nombre del método
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    List<UserEntity> findByIsBanned(Boolean isBanned);

    List<UserEntity> findByRole(Roles role);
}
