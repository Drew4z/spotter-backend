package com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.domain;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestResponse;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.in.GetContactRequestsUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.persistence.ContactRequestRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetContactRequestsService implements GetContactRequestsUseCase {

    private final ContactRequestRepositoryPort repositoryPort;

    @Override
    public List<ContactRequestResponse> getRequests(TrainerEntity trainer) {
        return repositoryPort.findByTrainerId(trainer.getId());
    }
}
