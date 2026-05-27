package com.spotter_proyect.spotter.core.useCases.client.requestContact.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.ContactRequestEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;
import com.spotter_proyect.spotter.core.shared.repositories.ContactRequestRepository;
import com.spotter_proyect.spotter.core.shared.repositories.TrainerRepository;
import com.spotter_proyect.spotter.core.useCases.client.requestContact.application.persistence.RequestContactRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestContactRepositoryAdapter implements RequestContactRepositoryPort {

    private final TrainerRepository trainerRepository;
    private final ContactRequestRepository contactRequestRepository;

    @Override
    public Optional<TrainerEntity> findTrainerById(Long trainerId) {
        return trainerRepository.findById(trainerId);
    }

    @Override
    public Optional<ContactRequestEntity> findActiveRequest(Long clientId, Long trainerId, List<RequestStatus> statuses) {
        return contactRequestRepository.findByClientIdAndTrainerIdAndStatusIn(clientId, trainerId, statuses);
    }

    @Override
    public ContactRequestEntity save(ContactRequestEntity contactRequest) {
        return contactRequestRepository.save(contactRequest);
    }
}
