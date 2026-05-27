package com.spotter_proyect.spotter.core.useCases.admin.verifyTrainer.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.TrainerRepository;
import com.spotter_proyect.spotter.core.useCases.admin.verifyTrainer.application.ports.in.VerifyTrainerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerifyTrainerService implements VerifyTrainerUseCase {

    private final TrainerRepository trainerRepository;
    private final Mapper mapper;

    @Override
    public List<UserResponse> getPendingTrainers() {
        return trainerRepository.findAll().stream()
                .filter(t -> Boolean.FALSE.equals(t.getIsVerified()) && Boolean.FALSE.equals(t.getIsBanned()))
                .map(mapper::UserEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void approveTrainer(Long trainerId) {
        TrainerEntity trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + trainerId));
        trainer.setIsVerified(true);
        trainerRepository.save(trainer);
    }

    @Override
    public void rejectTrainer(Long trainerId) {
        TrainerEntity trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + trainerId));
        trainer.setIsBanned(true);
        trainerRepository.save(trainer);
    }
}
