package com.spotter_proyect.spotter.core.shared.mapper;


import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.*;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.shared.model.Client;
import com.spotter_proyect.spotter.core.shared.model.Trainer;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponse;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterRequestDTO;
import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    //  MAPPER LOGIN METHODS
    public LoginResponse loginReqToResponse(String token, UserEntity user){
        return new LoginResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }


    //  MAPPER REGISTER METHODS
    public User registerReqToDomain(RegisterRequestDTO request) {
        // 1. Validamos el rol para saber qué constructor usar
        if (request.role() == Roles.TRAINER) {

            // 2. Retornamos un TRAINER (que implementa User)
            return new Trainer(
                    null,                       // ID (null porque aún no se guardó en BD)
                    request.name(),             // Name
                    request.email(),            // Email
                    request.password(),         // Password (ojo: aquí debería llegar encriptada o encriptarse antes)
                    request.role(),                  // Role
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
                    request.name(),             // Name
                    request.email(),            // Email
                    request.password(),         // Password
                    request.role(),                   // Role
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
            entity.setIsPremium(t.isPremium());
            entity.setCreatedAt(t.createdAt());
            // Específico
            entity.setBiography(t.biography());
            entity.setSpecialty(t.specialty());
            entity.setPhoneNumber(t.phoneNumber());
            entity.setIsVerified(t.isVerified());
            return entity;
        }
        if (userDomain instanceof Client c) {
            ClientEntity entity = new ClientEntity();
            entity.setName(c.name());
            entity.setEmail(c.email());
            entity.setPassword(c.password());
            entity.setRole(c.role());
            entity.setIsPremium(c.isPremium());
            entity.setCreatedAt(c.createdAt());

            // Específicos
            entity.setGoals(c.goals());
            return entity;
        }
        throw new IllegalArgumentException("Dominio desconocido: " + userDomain.getClass());
    }

    public RegisterResponseDTO authEntityToDomain(UserEntity userEntity) {
        if (userEntity instanceof TrainerEntity t) {

            return new RegisterResponseDTO(
                    t.getId(),
                    t.getName(),
                    t.getEmail(),
                    t.getRole()
            );

        } else if (userEntity instanceof ClientEntity c) {
            return new RegisterResponseDTO(
                    c.getId(),
                    c.getName(),
                    c.getEmail(),
                    c.getRole()
            );
        }
        throw new ResourceNotFoundException("The user could not be found");
    }

    //  MAPPER VIDEOS METHODS

    public VideoEntity uploadVideoRequestToEntity(VideoRequest request, UserEntity trainer){

        VideoEntity video = new VideoEntity();
        video.setTitle(request.title());
        video.setVideoUrl(request.videoUrl());
        video.setCategory(request.category());
        video.setCreatedAt(LocalDateTime.now());
        video.setLikesCount(0);
        video.setTrainerEntity(trainer); // A

        return video;
    }

    public VideoResponse videoEntityToResponse(VideoEntity video){

        return new VideoResponse(
                video.getId(),
                video.getTitle(),
                video.getVideoUrl(),
                video.getCategory(),
                video.getTrainerEntity().getName(), // Nombre del entrenador
                video.getLikesCount(),
                video.getCreatedAt()
        );
    }

    public List<VideoResponse> listVideosEntityToReponse(List<VideoEntity> videos) {
        return videos.stream()
                .map(this::videoEntityToResponse)
                .collect(Collectors.toList());
    }

    public VideoEntity modifyVideo(VideoRequest request, VideoEntity videoEntity) {

            videoEntity.setTitle(request.title());
            videoEntity.setCategory(request.category());
            videoEntity.setVideoUrl(request.videoUrl());

        return videoEntity;
    }

    public Page<VideoResponse> pageEntityToResponse(Page<VideoEntity> entityPage) {
        return entityPage.map(this::videoEntityToResponse);
    }

    // MAPPER ADMIN METHODS

    public List<UserResponse> listUserEntityToResponse(List<UserEntity> users) {
        return users.stream()
                .map(this::UserEntityToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse UserEntityToResponse(UserEntity user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    // MAPPER FOLLOW METHODS

    public FollowEntity fill(ClientEntity client, TrainerEntity trainer) {
        FollowEntity newFollow = new FollowEntity();
            newFollow.setClientEntity(client);
            newFollow.setTrainerEntity(trainer);
        return newFollow;
    }


}