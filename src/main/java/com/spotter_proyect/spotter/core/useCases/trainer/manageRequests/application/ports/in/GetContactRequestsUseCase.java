package com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.in;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestResponse;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;

import java.util.List;

public interface GetContactRequestsUseCase {
    List<ContactRequestResponse> getRequests(TrainerEntity trainer);
}
