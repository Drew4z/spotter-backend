package com.spotter_proyect.spotter.core.useCases.client.requestContact.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestRequest;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;

public interface RequestContactUseCase {

    String requestContact(ClientEntity client, Long trainerId, ContactRequestRequest request);
}
