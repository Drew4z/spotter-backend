package com.spotter_proyect.spotter.core.shared.repositories;

import com.spotter_proyect.spotter.core.shared.entities.LikeVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeVideoRepository extends JpaRepository<LikeVideoEntity, Long>{

    Optional<LikeVideoEntity> findByVideoIdAndUserId(Long videoId, Long userId);

}
