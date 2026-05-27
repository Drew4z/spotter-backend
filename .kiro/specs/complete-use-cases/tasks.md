# Implementation Plan: Complete Use Cases

## Overview

This implementation plan covers 16 use cases distributed across four functional areas for the Spotter backend platform. The implementation follows the existing hexagonal architecture pattern: Controller → UseCase → Orchestrator → Service → RepositoryPort → RepositoryAdapter. All code will be written in Java 21 with Spring Boot 4.

## Tasks

- [x] 1. Set up shared infrastructure components
  - Add `isBanned` field to `UserEntity` if not present
  - Create `ContactRequestRepository` JPA interface with custom query methods
  - Add new DTO records: `ContactRequestResponse`, `TrainerStatsResponse`, `ModifyProfileRequest`, `CreateAdminRequest`, `ContactRequestRequest`, `RespondRequestRequest`
  - Extend `Mapper` class with contact request mapping methods
  - _Requirements: 1.1, 2.1, 7.1, 8.3, 10.1, 15.1, 16.1_

- [ ]* 1.1 Write unit tests for Mapper extensions
  - Test contact request entity to response mapping
  - Test admin creation request to entity mapping
  - _Requirements: 2.1, 7.1_

- [x] 2. Implement Admin use case: Ban/Unban User
  - [x] 2.1 Create BanUser domain service
    - Implement `BanUserService` with toggle ban logic
    - Use `BanUserRepositoryPort` to find and save user
    - Throw `ResourceNotFoundException` if user not found
    - _Requirements: 1.1, 1.2, 1.3_
  
  - [x] 2.2 Create BanUser application layer
    - Create `BanUserUseCase` interface
    - Implement `BanUserOrchestrator` that delegates to service
    - Create `BanUserRepositoryPort` interface
    - Implement `BanUserRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 1.1_
  
  - [x] 2.3 Create BanUser REST controller
    - Implement `BanUserController` with `PUT /admin/users/ban/{id}` endpoint
    - Add `@PreAuthorize("hasAuthority('ADMIN')")` annotation
    - _Requirements: 1.1, 1.4_
  
  - [ ]* 2.4 Write unit tests for BanUserService
    - Test successful ban toggle (false → true)
    - Test successful unban toggle (true → false)
    - Test ResourceNotFoundException when user not found
    - _Requirements: 1.1, 1.2, 1.3_
  
  - [ ]* 2.5 Write integration tests for BanUser endpoint
    - Test successful ban with ADMIN role returns 200
    - Test 403 when non-admin tries to ban
    - Test 404 when user not found
    - _Requirements: 1.1, 1.2, 1.4_

- [x] 3. Implement Admin use case: Create Admin
  - [x] 3.1 Create CreateAdmin domain service
    - Implement `CreateAdminService` with admin creation logic
    - Use `PasswordEncoder` to encrypt password
    - Throw `DuplicateActionException` if email exists
    - _Requirements: 2.1, 2.2, 2.3_
  
  - [x] 3.2 Create CreateAdmin application layer
    - Create `CreateAdminUseCase` interface
    - Implement `CreateAdminOrchestrator`
    - Create `CreateAdminRepositoryPort` interface
    - Implement `CreateAdminRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 2.1_
  
  - [x] 3.3 Create CreateAdmin REST controller
    - Implement `CreateAdminController` with `POST /admin/createAdmin` endpoint
    - Add `@Valid` annotation for request validation
    - Add `@PreAuthorize("hasAuthority('ADMIN')")` annotation
    - _Requirements: 2.1, 2.4_
  
  - [ ]* 3.4 Write unit tests for CreateAdminService
    - Test successful admin creation
    - Test DuplicateActionException when email exists
    - Verify password is encrypted
    - _Requirements: 2.1, 2.2, 2.3_
  
  - [ ]* 3.5 Write integration tests for CreateAdmin endpoint
    - Test successful creation with valid data returns 200
    - Test 409 when email already exists
    - Test 400 when validation fails (invalid email, blank fields)
    - Test 403 when non-admin tries to create admin
    - _Requirements: 2.1, 2.2, 2.4_

- [x] 4. Implement Admin use case: Delete Inappropriate Video
  - [x] 4.1 Create DeleteVideo domain service
    - Implement `DeleteVideoService` with video deletion logic
    - Throw `ResourceNotFoundException` if video not found
    - _Requirements: 3.1, 3.2_
  
  - [x] 4.2 Create DeleteVideo application layer
    - Create `DeleteVideoUseCase` interface
    - Implement `DeleteVideoOrchestrator`
    - Create `DeleteVideoRepositoryPort` interface
    - Implement `DeleteVideoRepositoryAdapter` using shared `VideoRepository`
    - _Requirements: 3.1_
  
  - [x] 4.3 Create DeleteVideo REST controller
    - Implement `DeleteVideoController` with `DELETE /admin/videos/delete/{id}` endpoint
    - Add `@PreAuthorize("hasAuthority('ADMIN')")` annotation
    - _Requirements: 3.1, 3.4_
  
  - [ ]* 4.4 Write unit tests for DeleteVideoService
    - Test successful video deletion
    - Test ResourceNotFoundException when video not found
    - _Requirements: 3.1, 3.2_
  
  - [ ]* 4.5 Write integration tests for DeleteVideo endpoint
    - Test successful deletion returns 200
    - Test 404 when video not found
    - Test 403 when non-admin tries to delete
    - _Requirements: 3.1, 3.2, 3.4_

- [ ] 5. Checkpoint - Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [ ] 6. Implement Admin use case: Filter Banned Users
  - [ ] 6.1 Create GetBannedUsers domain service
    - Implement `GetBannedUsersService` that filters users by `isBanned = true`
    - _Requirements: 4.1_
  
  - [ ] 6.2 Create GetBannedUsers application layer
    - Create `GetBannedUsersUseCase` interface
    - Implement `GetBannedUsersOrchestrator`
    - Create `GetBannedUsersRepositoryPort` interface
    - Implement `GetBannedUsersRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 4.1_
  
  - [ ] 6.3 Create GetBannedUsers REST controller
    - Implement `GetBannedUsersController` with `GET /admin/users/banned` endpoint
    - Add `@PreAuthorize("hasAuthority('ADMIN')")` annotation
    - _Requirements: 4.1, 4.3_
  
  - [ ]* 6.4 Write unit tests for GetBannedUsersService
    - Test returns only banned users
    - Test returns empty list when no banned users
    - _Requirements: 4.1, 4.2_
  
  - [ ]* 6.5 Write integration tests for GetBannedUsers endpoint
    - Test returns correct banned users list
    - Test returns empty list when no banned users
    - Test 403 when non-admin tries to access
    - _Requirements: 4.1, 4.2, 4.3_

- [ ] 7. Implement Admin use case: Filter Users by Role
  - [ ] 7.1 Create GetUsersByRole domain service
    - Implement `GetUsersByRoleService` that filters users by role parameter
    - _Requirements: 5.1_
  
  - [ ] 7.2 Create GetUsersByRole application layer
    - Create `GetUsersByRoleUseCase` interface
    - Implement `GetUsersByRoleOrchestrator`
    - Create `GetUsersByRoleRepositoryPort` interface
    - Implement `GetUsersByRoleRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 5.1_
  
  - [ ] 7.3 Create GetUsersByRole REST controller
    - Implement `GetUsersByRoleController` with `GET /admin/users/byRole?role={role}` endpoint
    - Add `@PreAuthorize("hasAuthority('ADMIN')")` annotation
    - _Requirements: 5.1, 5.3_
  
  - [ ]* 7.4 Write unit tests for GetUsersByRoleService
    - Test returns only users with specified role
    - Test returns empty list when no users with that role
    - _Requirements: 5.1, 5.2_
  
  - [ ]* 7.5 Write integration tests for GetUsersByRole endpoint
    - Test returns correct users for CLIENT role
    - Test returns correct users for TRAINER role
    - Test returns empty list when no users with role
    - Test 403 when non-admin tries to access
    - _Requirements: 5.1, 5.2, 5.3_

- [x] 8. Implement Admin use case: Toggle Premium Status
  - [x] 8.1 Create TogglePremium domain service
    - Implement `TogglePremiumService` with toggle premium logic
    - Throw `ResourceNotFoundException` if user not found
    - _Requirements: 6.1, 6.2_
  
  - [x] 8.2 Create TogglePremium application layer
    - Create `TogglePremiumUseCase` interface
    - Implement `TogglePremiumOrchestrator`
    - Create `TogglePremiumRepositoryPort` interface
    - Implement `TogglePremiumRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 6.1_
  
  - [x] 8.3 Create TogglePremium REST controller
    - Implement `TogglePremiumController` with `PUT /admin/users/premium/{id}` endpoint
    - Add `@PreAuthorize("hasAuthority('ADMIN')")` annotation
    - _Requirements: 6.1, 6.4_
  
  - [ ]* 8.4 Write unit tests for TogglePremiumService
    - Test successful premium toggle (false → true)
    - Test successful premium toggle (true → false)
    - Test ResourceNotFoundException when user not found
    - _Requirements: 6.1, 6.2_
  
  - [ ]* 8.5 Write integration tests for TogglePremium endpoint
    - Test successful toggle returns 200
    - Test 404 when user not found
    - Test 403 when non-admin tries to toggle
    - _Requirements: 6.1, 6.2, 6.4_

- [ ] 9. Checkpoint - Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [x] 10. Implement Client use case: Request Contact with Trainer
  - [x] 10.1 Create RequestContact domain service
    - Implement `RequestContactService` with contact request creation logic
    - Throw `ResourceNotFoundException` if trainer not found
    - Throw `DuplicateActionException` if active request exists (PENDING or ACCEPTED)
    - Use `ContactRequestRepository` to check for duplicates
    - _Requirements: 7.1, 7.2, 7.3_
  
  - [x] 10.2 Create RequestContact application layer
    - Create `RequestContactUseCase` interface
    - Implement `RequestContactOrchestrator`
    - Create `RequestContactRepositoryPort` interface
    - Implement `RequestContactRepositoryAdapter` using `ContactRequestRepository` and `TrainerRepository`
    - _Requirements: 7.1_
  
  - [x] 10.3 Create RequestContact REST controller
    - Implement `RequestContactController` with `POST /client/contact/request/{trainerId}` endpoint
    - Extract authenticated client using `Utils.getUser(Authentication)`
    - Add `@Valid` annotation for request validation
    - _Requirements: 7.1, 7.4_
  
  - [ ]* 10.4 Write unit tests for RequestContactService
    - Test successful contact request creation
    - Test ResourceNotFoundException when trainer not found
    - Test DuplicateActionException when PENDING request exists
    - Test DuplicateActionException when ACCEPTED request exists
    - _Requirements: 7.1, 7.2, 7.3_
  
  - [ ]* 10.5 Write integration tests for RequestContact endpoint
    - Test successful request creation returns 200
    - Test 404 when trainer not found
    - Test 409 when duplicate request exists
    - Test 400 when message is blank
    - _Requirements: 7.1, 7.2, 7.3, 7.4_

- [ ] 11. Implement Client use case: Get My Contact Requests
  - [ ] 11.1 Create GetMyRequests domain service
    - Implement `GetMyRequestsService` that retrieves requests by client ID
    - Use `ContactRequestRepository.findByClientId()`
    - _Requirements: 8.1_
  
  - [ ] 11.2 Create GetMyRequests application layer
    - Create `GetMyRequestsUseCase` interface
    - Implement `GetMyRequestsOrchestrator`
    - Create `GetMyRequestsRepositoryPort` interface
    - Implement `GetMyRequestsRepositoryAdapter` using `ContactRequestRepository`
    - _Requirements: 8.1_
  
  - [ ] 11.3 Create GetMyRequests REST controller
    - Implement `GetMyRequestsController` with `GET /client/contact/myRequests` endpoint
    - Extract authenticated client using `Utils.getUser(Authentication)`
    - _Requirements: 8.1_
  
  - [ ]* 11.4 Write unit tests for GetMyRequestsService
    - Test returns all client requests
    - Test returns empty list when no requests
    - _Requirements: 8.1, 8.2_
  
  - [ ]* 11.5 Write integration tests for GetMyRequests endpoint
    - Test returns correct requests for authenticated client
    - Test returns empty list when no requests
    - Test response includes all required fields (id, trainer name, message, status, createdAt)
    - _Requirements: 8.1, 8.2, 8.3_

- [x] 12. Implement SharedUsers use case: Get User Profile
  - [x] 12.1 Create GetProfile domain service
    - Implement `GetProfileService` that retrieves user by ID
    - Throw `ResourceNotFoundException` if user not found
    - _Requirements: 9.1, 9.2_
  
  - [x] 12.2 Create GetProfile application layer
    - Create `GetProfileUseCase` interface
    - Implement `GetProfileOrchestrator`
    - Create `GetProfileRepositoryPort` interface
    - Implement `GetProfileRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 9.1_
  
  - [x] 12.3 Create GetProfile REST controller
    - Implement `GetProfileController` with `GET /profile/{id}` endpoint
    - No role restriction (any authenticated user can access)
    - _Requirements: 9.1_
  
  - [ ]* 12.4 Write unit tests for GetProfileService
    - Test successful profile retrieval
    - Test ResourceNotFoundException when user not found
    - _Requirements: 9.1, 9.2_
  
  - [ ]* 12.5 Write integration tests for GetProfile endpoint
    - Test successful retrieval returns 200
    - Test 404 when user not found
    - Test 401 when not authenticated
    - _Requirements: 9.1, 9.2_

- [ ] 13. Checkpoint - Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [x] 14. Implement SharedUsers use case: Modify Own Profile
  - [x] 14.1 Create ModifyProfile domain service
    - Implement `ModifyProfileService` that updates name and/or pathAvatar
    - Update only non-null fields from request
    - _Requirements: 10.1_
  
  - [x] 14.2 Create ModifyProfile application layer
    - Create `ModifyProfileUseCase` interface
    - Implement `ModifyProfileOrchestrator`
    - Create `ModifyProfileRepositoryPort` interface
    - Implement `ModifyProfileRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 10.1_
  
  - [x] 14.3 Create ModifyProfile REST controller
    - Implement `ModifyProfileController` with `PUT /profile/modify` endpoint
    - Extract authenticated user using `Utils.getUser(Authentication)`
    - _Requirements: 10.1_
  
  - [ ]* 14.4 Write unit tests for ModifyProfileService
    - Test successful name update
    - Test successful avatar update
    - Test successful update of both fields
    - _Requirements: 10.1_
  
  - [ ]* 14.5 Write integration tests for ModifyProfile endpoint
    - Test successful modification returns 200 with updated data
    - Test 401 when not authenticated
    - _Requirements: 10.1, 10.2_

- [ ] 15. Implement SharedUsers use case: Delete Own Profile
  - [ ] 15.1 Create DeleteProfile domain service
    - Implement `DeleteProfileService` that deletes authenticated user
    - _Requirements: 11.1_
  
  - [ ] 15.2 Create DeleteProfile application layer
    - Create `DeleteProfileUseCase` interface
    - Implement `DeleteProfileOrchestrator`
    - Create `DeleteProfileRepositoryPort` interface
    - Implement `DeleteProfileRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 11.1_
  
  - [ ] 15.3 Create DeleteProfile REST controller
    - Implement `DeleteProfileController` with `DELETE /profile/delete` endpoint
    - Extract authenticated user using `Utils.getUser(Authentication)`
    - _Requirements: 11.1_
  
  - [ ]* 15.4 Write unit tests for DeleteProfileService
    - Test successful profile deletion
    - _Requirements: 11.1_
  
  - [ ]* 15.5 Write integration tests for DeleteProfile endpoint
    - Test successful deletion returns 200
    - Test 401 when not authenticated
    - Verify user is actually deleted from database
    - _Requirements: 11.1, 11.2_

- [ ] 16. Implement SharedUsers use case: Search Users by Name
  - [ ] 16.1 Create SearchUsers domain service
    - Implement `SearchUsersService` with case-insensitive name search
    - Use `UserRepository` with `LIKE` query or `containsIgnoreCase`
    - _Requirements: 12.1_
  
  - [ ] 16.2 Create SearchUsers application layer
    - Create `SearchUsersUseCase` interface
    - Implement `SearchUsersOrchestrator`
    - Create `SearchUsersRepositoryPort` interface
    - Implement `SearchUsersRepositoryAdapter` using shared `UserRepository`
    - _Requirements: 12.1_
  
  - [ ] 16.3 Create SearchUsers REST controller
    - Implement `SearchUsersController` with `GET /search/users?name={name}` endpoint
    - _Requirements: 12.1_
  
  - [ ]* 16.4 Write unit tests for SearchUsersService
    - Test returns matching users (case-insensitive)
    - Test returns empty list when no matches
    - _Requirements: 12.1, 12.2_
  
  - [ ]* 16.5 Write integration tests for SearchUsers endpoint
    - Test returns correct users for partial name match
    - Test case-insensitive search works
    - Test returns empty list when no matches
    - _Requirements: 12.1, 12.2_

- [ ] 17. Implement SharedUsers use case: Search Videos by Category
  - [ ] 17.1 Create SearchVideos domain service
    - Implement `SearchVideosService` that filters by category
    - Use `VideoRepository` to find by category
    - _Requirements: 13.1_
  
  - [ ] 17.2 Create SearchVideos application layer
    - Create `SearchVideosUseCase` interface
    - Implement `SearchVideosOrchestrator`
    - Create `SearchVideosRepositoryPort` interface
    - Implement `SearchVideosRepositoryAdapter` using shared `VideoRepository`
    - _Requirements: 13.1_
  
  - [ ] 17.3 Create SearchVideos REST controller
    - Implement `SearchVideosController` with `GET /search/videos?category={category}` endpoint
    - _Requirements: 13.1_
  
  - [ ]* 17.4 Write unit tests for SearchVideosService
    - Test returns videos with matching category
    - Test returns empty list when no videos in category
    - _Requirements: 13.1, 13.2_
  
  - [ ]* 17.5 Write integration tests for SearchVideos endpoint
    - Test returns correct videos for category
    - Test returns empty list when no videos
    - _Requirements: 13.1, 13.2_

- [ ] 18. Checkpoint - Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [ ] 19. Implement SharedUsers use case: Get Followed Trainers
  - [ ] 19.1 Create GetFollowing domain service
    - Implement `GetFollowingService` that retrieves followed trainers for client
    - Use `FollowRepository` to find by client ID
    - _Requirements: 14.1_
  
  - [ ] 19.2 Create GetFollowing application layer
    - Create `GetFollowingUseCase` interface
    - Implement `GetFollowingOrchestrator`
    - Create `GetFollowingRepositoryPort` interface
    - Implement `GetFollowingRepositoryAdapter` using shared `FollowRepository`
    - _Requirements: 14.1_
  
  - [ ] 19.3 Create GetFollowing REST controller
    - Implement `GetFollowingController` with `GET /client/following` endpoint
    - Extract authenticated client using `Utils.getUser(Authentication)`
    - _Requirements: 14.1_
  
  - [ ]* 19.4 Write unit tests for GetFollowingService
    - Test returns all followed trainers
    - Test returns empty list when not following anyone
    - _Requirements: 14.1, 14.2_
  
  - [ ]* 19.5 Write integration tests for GetFollowing endpoint
    - Test returns correct followed trainers
    - Test returns empty list when not following anyone
    - _Requirements: 14.1, 14.2_

- [ ] 20. Implement Trainer use case: Get Trainer Stats
  - [ ] 20.1 Create GetStats domain service
    - Implement `GetStatsService` that calculates trainer statistics
    - Count followers using `FollowRepository`
    - Count videos using `VideoRepository`
    - Sum likes from all trainer videos
    - _Requirements: 15.1_
  
  - [ ] 20.2 Create GetStats application layer
    - Create `GetStatsUseCase` interface
    - Implement `GetStatsOrchestrator`
    - Create `GetStatsRepositoryPort` interface
    - Implement `GetStatsRepositoryAdapter` using `FollowRepository` and `VideoRepository`
    - _Requirements: 15.1_
  
  - [ ] 20.3 Create GetStats REST controller
    - Implement `GetStatsController` with `GET /trainer/stats` endpoint
    - Extract authenticated trainer using `Utils.getUser(Authentication)`
    - Add `@PreAuthorize("hasAuthority('TRAINER')")` annotation
    - _Requirements: 15.1_
  
  - [ ]* 20.4 Write unit tests for GetStatsService
    - Test returns correct stats when trainer has data
    - Test returns zeros when trainer has no followers/videos/likes
    - _Requirements: 15.1, 15.2_
  
  - [ ]* 20.5 Write integration tests for GetStats endpoint
    - Test returns correct stats for authenticated trainer
    - Test returns zeros when no data
    - Test 403 when non-trainer tries to access
    - _Requirements: 15.1, 15.2_

- [ ] 21. Implement Trainer use case: Get Received Contact Requests
  - [ ] 21.1 Create GetReceivedRequests domain service
    - Implement `GetReceivedRequestsService` that retrieves requests by trainer ID
    - Use `ContactRequestRepository.findByTrainerId()`
    - _Requirements: 16.1_
  
  - [ ] 21.2 Create GetReceivedRequests application layer
    - Create `GetReceivedRequestsUseCase` interface
    - Implement `GetReceivedRequestsOrchestrator`
    - Create `GetReceivedRequestsRepositoryPort` interface
    - Implement `GetReceivedRequestsRepositoryAdapter` using `ContactRequestRepository`
    - _Requirements: 16.1_
  
  - [ ] 21.3 Create GetReceivedRequests REST controller
    - Implement `GetReceivedRequestsController` with `GET /trainer/requests` endpoint
    - Extract authenticated trainer using `Utils.getUser(Authentication)`
    - Add `@PreAuthorize("hasAuthority('TRAINER')")` annotation
    - _Requirements: 16.1_
  
  - [ ]* 21.4 Write unit tests for GetReceivedRequestsService
    - Test returns all trainer requests
    - Test returns empty list when no requests
    - _Requirements: 16.1_
  
  - [ ]* 21.5 Write integration tests for GetReceivedRequests endpoint
    - Test returns correct requests for authenticated trainer
    - Test returns empty list when no requests
    - Test 403 when non-trainer tries to access
    - _Requirements: 16.1_

- [ ] 22. Implement Trainer use case: Respond to Contact Request
  - [ ] 22.1 Create RespondRequest domain service
    - Implement `RespondRequestService` that updates request status
    - Throw `ResourceNotFoundException` if request not found
    - Throw `UnauthorizedActionException` if trainer is not the recipient
    - _Requirements: 16.2, 16.3, 16.4_
  
  - [ ] 22.2 Create RespondRequest application layer
    - Create `RespondRequestUseCase` interface
    - Implement `RespondRequestOrchestrator`
    - Create `RespondRequestRepositoryPort` interface
    - Implement `RespondRequestRepositoryAdapter` using `ContactRequestRepository`
    - _Requirements: 16.2_
  
  - [ ] 22.3 Create RespondRequest REST controller
    - Implement `RespondRequestController` with `PUT /trainer/requests/respond/{requestId}` endpoint
    - Extract authenticated trainer using `Utils.getUser(Authentication)`
    - Add `@PreAuthorize("hasAuthority('TRAINER')")` annotation
    - Add `@Valid` annotation for request validation
    - _Requirements: 16.2_
  
  - [ ]* 22.4 Write unit tests for RespondRequestService
    - Test successful ACCEPTED response
    - Test successful REJECTED response
    - Test ResourceNotFoundException when request not found
    - Test UnauthorizedActionException when trainer is not recipient
    - _Requirements: 16.2, 16.3, 16.4_
  
  - [ ]* 22.5 Write integration tests for RespondRequest endpoint
    - Test successful ACCEPTED response returns 200
    - Test successful REJECTED response returns 200
    - Test 404 when request not found
    - Test 403 when trainer is not recipient
    - Test 403 when non-trainer tries to respond
    - _Requirements: 16.2, 16.3, 16.4, 16.5_

- [ ] 23. Final checkpoint - Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [ ] 24. Integration and verification
  - [ ] 24.1 Verify all endpoints are properly secured with Spring Security
    - Check all admin endpoints have `@PreAuthorize("hasAuthority('ADMIN')")`
    - Check trainer-specific endpoints have `@PreAuthorize("hasAuthority('TRAINER')")`
    - Verify authenticated user extraction works correctly
    - _Requirements: 1.4, 2.4, 3.4, 4.3, 5.3, 6.4, 15.1, 16.1_
  
  - [ ] 24.2 Verify all DTOs have proper validation annotations
    - Check `@NotBlank`, `@Email`, `@NotNull` annotations are present
    - Verify `@Valid` is used in controllers
    - _Requirements: 2.1, 7.1, 10.1, 16.2_
  
  - [ ] 24.3 Verify exception handling is consistent
    - Check all services throw appropriate exceptions
    - Verify `GlobalExceptionHandler` handles all custom exceptions
    - Test error response format matches `ApiErrorResponse`
    - _Requirements: 1.2, 2.2, 3.2, 7.2, 7.3, 9.2, 16.3, 16.4_
  
  - [ ]* 24.4 Run full test suite
    - Execute all unit tests
    - Execute all integration tests
    - Verify test coverage meets goals (80%+ for services)
    - _Requirements: All_

## Notes

- Tasks marked with `*` are optional and can be skipped for faster MVP
- Each task references specific requirements for traceability
- Checkpoints ensure incremental validation
- All code follows the existing hexagonal architecture pattern
- Shared components (repositories, entities, DTOs, mapper, utils) are reused throughout
- Spring Security annotations ensure proper authorization
- Jakarta Validation annotations ensure input validation
- Custom exceptions provide consistent error handling
