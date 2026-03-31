package com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.adapter.persistence;


import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.auth.register.application.port.persistence.RegisterRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterRepositoryAdapter implements RegisterRepositoryPort {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public RegisterResponseDTO save(User userDomain){

        // Mapeamos el usuario de dominio a entidad para guardarlo en la db
        UserEntity userToSave = mapper.registerDomainToEntity(userDomain);

        // Guardamos la entidad
        UserEntity userSaved = userRepository.save(userToSave);

        // Mapeamos la entidad a response para
        return mapper.authEntityToDomain(userSaved);
    }
}
