package com.spotter_proyect.spotter.core.shared.mapper;


import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.model.Client;
import com.spotter_proyect.spotter.core.shared.model.Trainer;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterRequestDTO;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {

    //  MAPPER REGISTER METHODS
    public User registerReqToDomain(RegisterRequestDTO request) {
        // 1. Validamos el rol para saber qué constructor usar
        if ("TRAINER".equalsIgnoreCase(request.role())) {

            // 2. Retornamos un TRAINER (que implementa User)
            return new Trainer(
                    null,                       // ID (null porque aún no se guardó en BD)
                    request.email(),            // Email
                    request.password(),         // Password (ojo: aquí debería llegar encriptada o encriptarse antes)
                    request.name(),             // Name
                    "TRAINER",                  // Role
                    false,                      // isPremium (default)
                    LocalDateTime.now(),        // createdAt
                    // Campos específicos de Trainer
                    request.specialty(),        // Specialty
                    null,                       // Biography (vacío por ahora)
                    null,                       // PhoneNumber (vacío por ahora)
                    false                       // isVerified (false por defecto)
            );
        } else {
            // 3. Retornamos un CLIENT (que implementa User)
            return new Client(
                    null,                       // ID
                    request.email(),            // Email
                    request.password(),         // Password
                    request.name(),             // Name
                    "CLIENT",                   // Role
                    false,                      // isPremium
                    LocalDateTime.now(),        // createdAt
                    // Campos específicos de Client
                    request.goals()             // Goals
            );
        }
    }

    public UserEntity registerDomainToEntity(User userDomain) {
        if (userDomain instanceof Trainer t) {
            TrainerEntity entity = new TrainerEntity();
            // Mapeo manual de campos
            entity.setName(t.name());
            entity.setEmail(t.email());
            entity.setPassword(t.password());
            entity.setRole(t.role());
            entity.setCreatedAt(t.createdAt());
            entity.setIsPremium(t.isPremium());
            entity.setSpecialty(t.specialty()); // Específico
            entity.setBiography(t.biography());
            entity.setPhoneNumber(t.phoneNumber());
            return entity;
        }
        if (userDomain instanceof Client c) {
            ClientEntity entity = new ClientEntity();
            entity.setName(c.name());
            entity.setEmail(c.email());
            entity.setPassword(c.password());
            entity.setRole(c.role());
            entity.setCreatedAt(c.createdAt());
            entity.setIsPremium(c.isPremium());

            // Específicos
            entity.setGoals(c.goals());
            return entity;
        }
        throw new IllegalArgumentException("Dominio desconocido: " + userDomain.getClass());
    }

    public User authEntityToDomain(UserEntity userEntity) {
        if (userEntity instanceof TrainerEntity t) {
            return new Trainer(
                    t.getId(),
                    t.getName(),
                    t.getEmail(),
                    t.getPassword(),
                    t.getRole(),
                    t.getIsPremium(),
                    t.getCreatedAt(),
                    t.getSpecialty(),
                    t.getBiography(),
                    t.getPhoneNumber(),
                    t.getIsVerified()
            );
        } else if (userEntity instanceof ClientEntity c) {
            return new Client(
                    c.getId(),
                    c.getEmail(),
                    c.getPassword(),
                    c.getName(),
                    c.getRole(),
                    c.getIsPremium(),
                    c.getCreatedAt(),
                    c.getGoals()
            );
        }
        throw new IllegalArgumentException("Entidad desconocida: " + userEntity.getClass());
    }

    //  MAPPER VIDEOS METHODS

    public VideoEntity uploadVideoRequestToEntity(VideoRequest request, UserEntity trainer){

        VideoEntity video = new VideoEntity();
        video.setTitle(request.title());
        video.setVideoUrl(request.videoUrl());
        video.setCategory(request.category());
        video.setCreatedAt(LocalDateTime.now());
        video.setLikesCount(0);
        video.setTrainer(trainer); // A

        return video;
    }

    public VideoResponse uploadVideoEntityToResponse(VideoEntity video){

        return new VideoResponse(
                video.getId(),
                video.getTitle(),
                video.getVideoUrl(),
                video.getCategory(),
                video.getTrainer().getName(), // Nombre del entrenador
                video.getLikesCount(),
                video.getCreatedAt()
        );
    }
}