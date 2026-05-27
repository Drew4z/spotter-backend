package com.spotter_proyect.spotter.core.shared.repositories;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    // Método para obtener todos los videos de un trainer por su ID
    List<VideoEntity> findAllByTrainerEntityIdOrderByCreatedAtDesc(Long trainerId);

    // Método para obtener el feed de forma aleatoria
    // Usamos SQL nativo de Postgres: ORDER BY RANDOM() y LIMIT
    @Query(value = "SELECT * FROM videos ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<VideoEntity> findRandomVideos(@Param("limit") int limit);


    // Método para obtener el following feed
    @Query("SELECT v FROM VideoEntity v WHERE v.trainerEntity.id IN " +
            "(SELECT f.trainerEntity.id FROM FollowEntity f WHERE f.clientEntity.id = :clientId) " +
            "ORDER BY v.createdAt DESC") // Asumo que tienes un campo de fecha de creación
    Page<VideoEntity> findVideosFromFollowedTrainers(@Param("clientId") Long clientId, Pageable pageable);
}
