package com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.adapter.persistence;


import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.auth.register.application.port.persistence.RegisterRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterRepositoryAdapter implements RegisterRepositoryPort {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public User save(User userDomain){
        UserEntity userToSave = mapper.registerDomainToEntity(userDomain);
        UserEntity userSaved = userRepository.save(userToSave);

        return mapper.authEntityToDomain(userSaved);
    }
}
