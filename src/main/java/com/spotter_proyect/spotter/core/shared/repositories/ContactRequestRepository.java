package com.spotter_proyect.spotter.core.shared.repositories;

import com.spotter_proyect.spotter.core.shared.entities.ContactRequestEntity;
import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequestEntity, Long> {
    
    List<ContactRequestEntity> findByClientId(Long clientId);
    
    List<ContactRequestEntity> findByTrainerId(Long trainerId);
    
    Optional<ContactRequestEntity> findByClientIdAndTrainerIdAndStatusIn(
        Long clientId, 
        Long trainerId, 
        List<RequestStatus> statuses
    );
}
