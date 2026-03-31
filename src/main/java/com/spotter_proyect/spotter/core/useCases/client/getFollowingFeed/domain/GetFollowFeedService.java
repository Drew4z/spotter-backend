package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.repositories.ClientRepository;
import com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.persistence.GetFollowFeedRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFollowFeedService {

    private final GetFollowFeedRepositoryPort repositoryPort;
    private final ClientRepository clientRepository;

    public Page<VideoResponse> getFeed(int page, int size){

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ClientEntity client = clientRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado al usuario"));

        Long clientId = client.getId();

        Pageable pageable = PageRequest.of(page, size);

        return repositoryPort.getFollowFeed(clientId, pageable);
    }
}
