package com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestResponse;
import com.spotter_proyect.spotter.core.shared.entities.ContactRequestEntity;
import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;
import com.spotter_proyect.spotter.core.shared.repositories.ContactRequestRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.persistence.ContactRequestRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContactRequestRepositoryAdapter implements ContactRequestRepositoryPort {

    private final ContactRequestRepository repository;

    @Override
    public List<ContactRequestResponse> findByTrainerId(Long trainerId) {
        return repository.findByTrainerId(trainerId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long requestId, RequestStatus status) {
        repository.findById(requestId).ifPresent(req -> {
            req.setStatus(status);
            repository.save(req);
        });
    }

    private ContactRequestResponse toResponse(ContactRequestEntity entity) {
        String clientName  = entity.getClient()  != null ? entity.getClient().getName()  : "Cliente";
        String trainerName = entity.getTrainer() != null ? entity.getTrainer().getName() : "Entrenador";
        return new ContactRequestResponse(
                entity.getId(),
                trainerName,
                clientName,
                entity.getMessage(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
