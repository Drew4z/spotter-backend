package com.spotter_proyect.spotter.core.useCases.client.requestContact.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.DuplicateActionException;
import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestRequest;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.entities.ContactRequestEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;
import com.spotter_proyect.spotter.core.useCases.client.requestContact.application.persistence.RequestContactRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestContactService {

    private final RequestContactRepositoryPort repositoryPort;

    public String requestContact(ClientEntity client, Long trainerId, ContactRequestRequest request) {
        // Verify trainer exists
        TrainerEntity trainer = repositoryPort.findTrainerById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al entrenador"));

        // Check for duplicate active request (PENDING or ACCEPTED)
        boolean activeRequestExists = repositoryPort.findActiveRequest(
                client.getId(),
                trainerId,
                List.of(RequestStatus.PENDING, RequestStatus.ACCEPTED)
        ).isPresent();

        if (activeRequestExists) {
            throw new DuplicateActionException("Ya tienes una solicitud activa con este entrenador");
        }

        // Create and persist the contact request
        ContactRequestEntity contactRequest = new ContactRequestEntity();
        contactRequest.setClient(client);
        contactRequest.setTrainer(trainer);
        contactRequest.setMessage(request.message());

        repositoryPort.save(contactRequest);

        return "Solicitud de contacto enviada exitosamente";
    }
}
