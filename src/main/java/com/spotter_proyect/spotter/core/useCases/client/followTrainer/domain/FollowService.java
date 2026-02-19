package com.spotter_proyect.spotter.core.useCases.client.followTrainer.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.DuplicateActionException;
import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.repositories.FollowRepository;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.persistence.FollowRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepositoryPort repositoryPort;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public String followTrainer(Long trainerId){
        // 1. Conseguir el id del cliente y verificar que existe
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = auth.getName();
        UserEntity user = userRepository.findByEmail(emailUser)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado al usuario"));

        Long clientId = user.getId();

        // 2. Verificar que el entrenador existe
        UserEntity trainer = userRepository.findById(trainerId)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado al entrenador"));

        // 3. SEGURIDAD: Verificar que el usuario actual es el dueño del video
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!trainer.getEmail().equals(email)) {
            throw new RuntimeException("No tienes permiso para editar este video ⛔");
        }

        // 4. Le preguntas a la base de datos: "¿Este cliente YA sigue a este entrenador?"
        boolean alreadyFollow = followRepository.existsByClientIdAndTrainerId(clientId, trainerId);

        // 5. Verificamos que no le siga
        if (alreadyFollow) {
            // Detonas el error. El código se detiene AQUÍ MISMO y salta directo a tu GlobalExceptionHandler
            throw new DuplicateActionException("Ya estás siguiendo a este entrenador.");
        }

        return repositoryPort.followTrainer(clientId, trainerId);
    }
}