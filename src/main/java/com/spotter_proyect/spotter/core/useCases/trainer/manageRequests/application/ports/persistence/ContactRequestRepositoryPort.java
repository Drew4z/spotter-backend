package com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestResponse;
import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;

import java.util.List;

public interface ContactRequestRepositoryPort {
    List<ContactRequestResponse> findByTrainerId(Long trainerId);
    void updateStatus(Long requestId, RequestStatus status);
}
