package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.domain;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application.persistence.GetBannedUsersRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBannedUsersService {

    private final GetBannedUsersRepositoryPort repositoryPort;

    public List<UserEntity> getBannedUsers() {
        return repositoryPort.findByIsBanned(true);
    }
}
