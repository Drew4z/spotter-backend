package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application.persistence.GetBannedUsersRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminGetBannedUsersRepositoryAdapter")
@RequiredArgsConstructor
public class GetBannedUsersRepositoryAdapter implements GetBannedUsersRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> findByIsBanned(Boolean isBanned) {
        return userRepository.findByIsBanned(isBanned);
    }
}
