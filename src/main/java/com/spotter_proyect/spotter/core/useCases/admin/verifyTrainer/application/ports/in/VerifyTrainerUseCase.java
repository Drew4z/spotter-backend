package com.spotter_proyect.spotter.core.useCases.admin.verifyTrainer.application.ports.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import java.util.List;

public interface VerifyTrainerUseCase {
    List<UserResponse> getPendingTrainers();
    void approveTrainer(Long trainerId);
    void rejectTrainer(Long trainerId);
}
