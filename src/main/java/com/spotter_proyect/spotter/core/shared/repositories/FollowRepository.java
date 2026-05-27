package com.spotter_proyect.spotter.core.shared.repositories;

import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.entities.FollowEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

    // 1. Para contar cuántos seguidores tiene un entrenador (devuelve un número)
    Long countByTrainerEntity(TrainerEntity trainerEntity);

    // 2. Para comprobar si un cliente YA sigue a un entrenador (devuelve true o false)
    boolean existsByClientEntityAndTrainerEntity(ClientEntity clientEntity, TrainerEntity trainerEntity);

    // 3. Para buscar el registro exacto (útil para cuando el cliente quiera "Dejar de seguir" y necesites borrarlo)
    Optional<FollowEntity> findByClientEntityAndTrainerEntity(ClientEntity clientEntity, TrainerEntity trainerEntity);

    // 4. Para contar a cuántos entrenadores sigue un cliente
    Long countByClientEntity(ClientEntity clientEntity);

    // 5. Para obtener la lista de seguimientos de un cliente
    java.util.List<FollowEntity> findByClientEntity(ClientEntity clientEntity);

    // 6. Para obtener la lista de seguidores de un entrenador
    java.util.List<FollowEntity> findByTrainerEntity(TrainerEntity trainerEntity);
}
