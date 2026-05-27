package com.spotter_proyect.spotter.core.useCases.client.requestContact.application;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestRequest;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.useCases.client.requestContact.application.in.RequestContactUseCase;
import com.spotter_proyect.spotter.core.useCases.client.requestContact.domain.RequestContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestContactOrchestrator implements RequestContactUseCase {

    private final RequestContactService service;

    @Override
    public String requestContact(ClientEntity client, Long trainerId, ContactRequestRequest request) {
        return service.requestContact(client, trainerId, request);
    }
}
