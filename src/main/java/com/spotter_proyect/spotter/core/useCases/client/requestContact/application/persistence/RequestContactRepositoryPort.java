package com.spotter_proyect.spotter.core.useCases.client.requestContact.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.ContactRequestEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;

import java.util.List;
import java.util.Optional;

public interface RequestContactRepositoryPort {

    Optional<TrainerEntity> findTrainerById(Long trainerId);

    Optional<ContactRequestEntity> findActiveRequest(Long clientId, Long trainerId, List<RequestStatus> statuses);

    ContactRequestEntity save(ContactRequestEntity contactRequest);
}
