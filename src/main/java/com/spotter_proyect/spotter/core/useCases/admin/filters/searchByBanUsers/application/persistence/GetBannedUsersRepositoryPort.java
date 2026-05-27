package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import java.util.List;

public interface GetBannedUsersRepositoryPort {
    List<UserEntity> findByIsBanned(Boolean isBanned);
}
