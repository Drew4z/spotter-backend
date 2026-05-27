# Documento de Diseño: Complete Use Cases

## Overview

Este documento describe el diseño técnico para completar los casos de uso pendientes del backend de **Spotter**, una plataforma que conecta clientes con entrenadores personales. El sistema está construido con Java 21 y Spring Boot 4, siguiendo una arquitectura hexagonal (puertos y adaptadores).

### Alcance

El diseño cubre 16 casos de uso distribuidos en cuatro áreas funcionales:

1. **Administración (Admin)**: Gestión de usuarios, contenido y moderación de la plataforma
2. **Gestión de Perfil (SharedUsers)**: Operaciones de perfil disponibles para todos los usuarios autenticados
3. **Interacción Cliente-Entrenador**: Solicitudes de contacto y seguimiento
4. **Estadísticas de Entrenador**: Métricas de alcance y engagement

### Objetivos del Diseño

- Mantener consistencia con la arquitectura hexagonal existente
- Reutilizar componentes compartidos (repositorios, mappers, utilidades)
- Implementar autorización basada en roles usando Spring Security
- Garantizar validación de datos y manejo de errores robusto
- Seguir el patrón establecido: `Controller → UseCase → Orchestrator → Service → RepositoryPort → RepositoryAdapter`

---

## Architecture

### Architectural Pattern

El proyecto sigue una **arquitectura hexagonal (puertos y adaptadores)** con las siguientes capas:

```
┌─────────────────────────────────────────────────────────────┐
│                    Infrastructure Layer                      │
│  ┌──────────────────┐              ┌──────────────────┐    │
│  │   Controllers    │              │  Repository      │    │
│  │   (Adapters In)  │              │  Adapters        │    │
│  └────────┬─────────┘              └────────▲─────────┘    │
│           │                                  │              │
└───────────┼──────────────────────────────────┼──────────────┘
            │                                  │
┌───────────▼──────────────────────────────────┼──────────────┐
│                   Application Layer          │              │
│  ┌──────────────────┐              ┌─────────┴─────────┐   │
│  │   Use Cases      │              │  Repository       │   │
│  │   (Interfaces)   │              │  Ports            │   │
│  └────────┬─────────┘              └───────────────────┘   │
│           │                                                 │
│  ┌────────▼─────────┐                                      │
│  │   Orchestrators  │                                      │
│  └────────┬─────────┘                                      │
└───────────┼──────────────────────────────────────────────────┘
            │
┌───────────▼──────────────────────────────────────────────────┐
│                      Domain Layer                            │
│  ┌──────────────────┐       ┌──────────────────┐           │
│  │    Services      │       │   Domain Models  │           │
│  │  (Business Logic)│       │   (Entities)     │           │
│  └──────────────────┘       └──────────────────┘           │
└──────────────────────────────────────────────────────────────┘
```

### Layer Responsibilities

**Infrastructure Layer (Adapters)**:
- **Controllers**: Exponen endpoints REST, validan entrada HTTP, manejan autenticación/autorización
- **Repository Adapters**: Implementan los puertos de repositorio usando JPA repositories compartidos

**Application Layer**:
- **Use Cases (Interfaces)**: Definen contratos de operaciones de negocio
- **Orchestrators**: Implementan use cases, coordinan llamadas a servicios de dominio
- **Repository Ports (Interfaces)**: Abstraen el acceso a datos

**Domain Layer**:
- **Services**: Contienen lógica de negocio pura, usan repository ports
- **Domain Models**: Representan conceptos de negocio (User, Trainer, Client, etc.)

### Shared Components

El proyecto ya cuenta con componentes compartidos que serán reutilizados:

- **Repositories JPA**: `UserRepository`, `TrainerRepository`, `ClientRepository`, `FollowRepository`, `VideoRepository`
- **Entities**: `UserEntity`, `TrainerEntity`, `ClientEntity`, `ContactRequestEntity`, `FollowEntity`, `VideoEntity`
- **DTOs**: `UserResponse`, `VideoResponse`, `VideoRequest`
- **Mapper**: Conversión entre entities, DTOs y domain models
- **Utils**: Extracción de usuario autenticado desde JWT token
- **Exception Handlers**: `ResourceNotFoundException`, `DuplicateActionException`, `UnauthorizedActionException`, `UnauthenticatedException`

---

## Components and Interfaces

### Component Organization

Cada caso de uso sigue la estructura de directorios:

```
useCases/{area}/{useCaseName}/
├── application/
│   ├── in/
│   │   └── {UseCaseName}UseCase.java          # Interface del use case
│   ├── persistence/
│   │   └── {UseCaseName}RepositoryPort.java   # Interface del repositorio
│   └── {UseCaseName}Orchestrator.java         # Implementación del use case
├── domain/
│   └── {UseCaseName}Service.java              # Lógica de negocio
└── infrastructure/
    └── adapter/
        ├── in/
        │   └── {UseCaseName}Controller.java   # REST Controller
        └── persistence/
            └── {UseCaseName}RepositoryAdapter.java  # Implementación del port
```

### New Components Required

#### 1. Admin Use Cases

**BanUser**:
- `BanUserUseCase` (interface)
- `BanUserOrchestrator` (implements UseCase)
- `BanUserService` (business logic)
- `BanUserRepositoryPort` (interface)
- `BanUserRepositoryAdapter` (implements Port)
- `BanUserController` (REST endpoint)

**CreateAdmin**:
- `CreateAdminUseCase`
- `CreateAdminOrchestrator`
- `CreateAdminService`
- `CreateAdminRepositoryPort`
- `CreateAdminRepositoryAdapter`
- `CreateAdminController`

**DeleteInappropriateVideo**:
- `DeleteVideoUseCase`
- `DeleteVideoOrchestrator`
- `DeleteVideoService`
- `DeleteVideoRepositoryPort`
- `DeleteVideoRepositoryAdapter`
- `DeleteVideoController`

**FilterBannedUsers**:
- `GetBannedUsersUseCase`
- `GetBannedUsersOrchestrator`
- `GetBannedUsersService`
- `GetBannedUsersRepositoryPort`
- `GetBannedUsersRepositoryAdapter`
- `GetBannedUsersController`

**FilterUsersByRole**:
- `GetUsersByRoleUseCase`
- `GetUsersByRoleOrchestrator`
- `GetUsersByRoleService`
- `GetUsersByRoleRepositoryPort`
- `GetUsersByRoleRepositoryAdapter`
- `GetUsersByRoleController`

**ManagePremiumStatus**:
- `TogglePremiumUseCase`
- `TogglePremiumOrchestrator`
- `TogglePremiumService`
- `TogglePremiumRepositoryPort`
- `TogglePremiumRepositoryAdapter`
- `TogglePremiumController`

#### 2. Client Use Cases

**RequestContact**:
- `RequestContactUseCase`
- `RequestContactOrchestrator`
- `RequestContactService`
- `RequestContactRepositoryPort`
- `RequestContactRepositoryAdapter`
- `RequestContactController`

**GetMyContactRequests**:
- `GetMyRequestsUseCase`
- `GetMyRequestsOrchestrator`
- `GetMyRequestsService`
- `GetMyRequestsRepositoryPort`
- `GetMyRequestsRepositoryAdapter`
- `GetMyRequestsController`

#### 3. SharedUsers Use Cases

**GetUserProfile**:
- `GetProfileUseCase`
- `GetProfileOrchestrator`
- `GetProfileService`
- `GetProfileRepositoryPort`
- `GetProfileRepositoryAdapter`
- `GetProfileController`

**ModifyOwnProfile**:
- `ModifyProfileUseCase`
- `ModifyProfileOrchestrator`
- `ModifyProfileService`
- `ModifyProfileRepositoryPort`
- `ModifyProfileRepositoryAdapter`
- `ModifyProfileController`

**DeleteOwnProfile**:
- `DeleteProfileUseCase`
- `DeleteProfileOrchestrator`
- `DeleteProfileService`
- `DeleteProfileRepositoryPort`
- `DeleteProfileRepositoryAdapter`
- `DeleteProfileController`

**SearchUsersByName**:
- `SearchUsersUseCase`
- `SearchUsersOrchestrator`
- `SearchUsersService`
- `SearchUsersRepositoryPort`
- `SearchUsersRepositoryAdapter`
- `SearchUsersController`

**SearchVideosByCategory**:
- `SearchVideosUseCase`
- `SearchVideosOrchestrator`
- `SearchVideosService`
- `SearchVideosRepositoryPort`
- `SearchVideosRepositoryAdapter`
- `SearchVideosController`

**GetFollowedTrainers**:
- `GetFollowingUseCase`
- `GetFollowingOrchestrator`
- `GetFollowingService`
- `GetFollowingRepositoryPort`
- `GetFollowingRepositoryAdapter`
- `GetFollowingController`

#### 4. Trainer Use Cases

**GetTrainerStats**:
- `GetStatsUseCase`
- `GetStatsOrchestrator`
- `GetStatsService`
- `GetStatsRepositoryPort`
- `GetStatsRepositoryAdapter`
- `GetStatsController`

**ManageContactRequests**:
- `GetReceivedRequestsUseCase`
- `GetReceivedRequestsOrchestrator`
- `GetReceivedRequestsService`
- `GetReceivedRequestsRepositoryPort`
- `GetReceivedRequestsRepositoryAdapter`
- `GetReceivedRequestsController`
- `RespondRequestUseCase`
- `RespondRequestOrchestrator`
- `RespondRequestService`
- `RespondRequestRepositoryPort`
- `RespondRequestRepositoryAdapter`
- `RespondRequestController`

### New DTOs Required

**ContactRequestResponse**:
```java
public record ContactRequestResponse(
    Long id,
    String trainerName,
    String clientName,
    String message,
    RequestStatus status,
    LocalDateTime createdAt
) {}
```

**TrainerStatsResponse**:
```java
public record TrainerStatsResponse(
    Long totalFollowers,
    Long totalVideos,
    Long totalLikes
) {}
```

**ModifyProfileRequest**:
```java
public record ModifyProfileRequest(
    String name,
    String pathAvatar
) {}
```

**CreateAdminRequest**:
```java
public record CreateAdminRequest(
    @NotBlank String name,
    @NotBlank @Email String email,
    @NotBlank String password
) {}
```

**ContactRequestRequest**:
```java
public record ContactRequestRequest(
    @NotBlank String message
) {}
```

**RespondRequestRequest**:
```java
public record RespondRequestRequest(
    @NotNull RequestStatus status  // ACCEPTED or REJECTED
) {}
```

### New Repository Required

**ContactRequestRepository** (JPA Repository):
```java
public interface ContactRequestRepository extends JpaRepository<ContactRequestEntity, Long> {
    List<ContactRequestEntity> findByClientId(Long clientId);
    List<ContactRequestEntity> findByTrainerId(Long trainerId);
    Optional<ContactRequestEntity> findByClientIdAndTrainerIdAndStatusIn(
        Long clientId, 
        Long trainerId, 
        List<RequestStatus> statuses
    );
}
```

### Mapper Extensions

Agregar métodos al `Mapper` compartido:

```java
// Contact Request mappings
ContactRequestResponse contactRequestEntityToResponse(ContactRequestEntity entity);
List<ContactRequestResponse> listContactRequestEntityToResponse(List<ContactRequestEntity> entities);

// Admin creation
UserEntity createAdminRequestToEntity(CreateAdminRequest request, String encodedPassword);
```

---

## Data Models

### Existing Entities

**UserEntity** (base class):
- `id`: Long (PK)
- `name`: String
- `email`: String (unique)
- `password`: String (encrypted)
- `role`: Roles enum (ADMIN, CLIENT, TRAINER)
- `pathAvatar`: String
- `isPremium`: Boolean
- `isBanned`: Boolean (NOTA: Este campo debe agregarse si no existe)
- `createdAt`: LocalDateTime

**TrainerEntity** (extends UserEntity):
- `specialty`: SpecialityTrainer enum
- `biography`: String
- `phoneNumber`: String
- `isVerified`: Boolean

**ClientEntity** (extends UserEntity):
- `goals`: GoalsClient enum

**ContactRequestEntity**:
- `id`: Long (PK)
- `client`: ClientEntity (ManyToOne)
- `trainer`: TrainerEntity (ManyToOne)
- `status`: RequestStatus enum (PENDING, ACCEPTED, REJECTED)
- `message`: String
- `createdAt`: LocalDateTime

**FollowEntity**:
- `id`: Long (PK)
- `clientEntity`: ClientEntity (ManyToOne)
- `trainerEntity`: TrainerEntity (ManyToOne)

**VideoEntity**:
- `id`: Long (PK)
- `title`: String
- `videoUrl`: String
- `frontPagePath`: String
- `category`: VideoCategory enum
- `trainerEntity`: UserEntity (ManyToOne)
- `likesCount`: Integer
- `createdAt`: LocalDateTime

### Required Entity Modifications

**UserEntity** debe incluir el campo `isBanned`:
```java
@Column(nullable = false)
private Boolean isBanned = false;
```

Si este campo no existe, debe agregarse con una migración de base de datos.

### Database Relationships

```
UserEntity (1) ──< (N) VideoEntity
    │
    ├─── TrainerEntity (1) ──< (N) ContactRequestEntity
    │                      └──< (N) FollowEntity
    │
    └─── ClientEntity (1) ──< (N) ContactRequestEntity
                          └──< (N) FollowEntity
```

---

## Error Handling

### Exception Strategy

El sistema utiliza excepciones personalizadas manejadas por `GlobalExceptionHandler`:

**ResourceNotFoundException** (404):
- Usuario no encontrado
- Entrenador no encontrado
- Vídeo no encontrado
- Solicitud de contacto no encontrada

**DuplicateActionException** (409):
- Email ya existe al crear admin
- Solicitud de contacto duplicada (PENDING o ACCEPTED con mismo entrenador)

**UnauthorizedActionException** (403):
- Entrenador intenta responder solicitud que no le pertenece

**UnauthenticatedException** (401):
- Token JWT inválido o ausente

### Validation Strategy

**Input Validation**:
- Usar anotaciones de Jakarta Validation (`@NotBlank`, `@Email`, `@NotNull`)
- Validar en DTOs de request antes de llegar al service
- Spring Boot valida automáticamente con `@Valid` en controllers

**Business Logic Validation**:
- Verificar existencia de recursos antes de operaciones
- Validar estados de entidades (ej: no permitir solicitud duplicada)
- Verificar permisos de usuario (ej: solo el entrenador destinatario puede responder)

**Authorization Validation**:
- Usar `@PreAuthorize` en controllers para verificar roles
- Extraer usuario autenticado con `Utils.getUser(Authentication)`
- Comparar IDs para operaciones que requieren ownership

### Error Response Format

Todas las excepciones devuelven `ApiErrorResponse`:
```java
public record ApiErrorResponse(
    String message,
    int status,
    LocalDateTime timestamp
) {}
```

---

## Testing Strategy

### Testing Approach

Dado que este feature involucra principalmente operaciones CRUD con Spring Boot, JPA, y autorización, **property-based testing NO es apropiado**. En su lugar, utilizaremos:

1. **Unit Tests**: Para lógica de negocio en Services
2. **Integration Tests**: Para endpoints REST completos
3. **Repository Tests**: Para queries JPA personalizadas

### Unit Testing

**Objetivo**: Verificar lógica de negocio en Services de forma aislada.

**Herramientas**:
- JUnit 5
- Mockito para mocking de repository ports
- AssertJ para assertions

**Cobertura**:
- Cada Service debe tener tests que verifiquen:
  - Casos exitosos (happy path)
  - Validaciones de negocio
  - Lanzamiento de excepciones apropiadas

**Ejemplo de test para BanUserService**:
```java
@ExtendWith(MockitoExtension.class)
class BanUserServiceTest {
    
    @Mock
    private BanUserRepositoryPort repositoryPort;
    
    @InjectMocks
    private BanUserService service;
    
    @Test
    void shouldToggleBanStatus_whenUserExists() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setIsBanned(false);
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(user));
        
        // When
        String result = service.toggleBan(1L);
        
        // Then
        verify(repositoryPort).save(user);
        assertTrue(user.getIsBanned());
        assertThat(result).contains("baneado");
    }
    
    @Test
    void shouldThrowException_whenUserNotFound() {
        // Given
        when(repositoryPort.findById(999L)).thenReturn(Optional.empty());
        
        // When & Then
        assertThatThrownBy(() -> service.toggleBan(999L))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessageContaining("No se ha encontrado al usuario");
    }
}
```

### Integration Testing

**Objetivo**: Verificar el flujo completo desde HTTP request hasta base de datos.

**Herramientas**:
- `@SpringBootTest` con `@AutoConfigureMockMvc`
- `MockMvc` para simular requests HTTP
- `@Sql` para preparar datos de test
- Base de datos H2 en memoria o Testcontainers con PostgreSQL

**Cobertura**:
- Cada endpoint debe tener tests que verifiquen:
  - Respuestas exitosas con datos correctos
  - Códigos de estado HTTP apropiados
  - Autorización (roles correctos pueden acceder, roles incorrectos reciben 403)
  - Validación de entrada (requests inválidos reciben 400)
  - Manejo de errores (recursos no encontrados reciben 404)

**Ejemplo de test para BanUserController**:
```java
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/test-data/users.sql")
class BanUserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @WithMockUser(authorities = "ADMIN")
    void shouldBanUser_whenAdminMakesRequest() throws Exception {
        mockMvc.perform(put("/admin/users/ban/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("baneado")));
    }
    
    @Test
    @WithMockUser(authorities = "CLIENT")
    void shouldReturn403_whenNonAdminTriesToBan() throws Exception {
        mockMvc.perform(put("/admin/users/ban/1"))
            .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(authorities = "ADMIN")
    void shouldReturn404_whenUserNotFound() throws Exception {
        mockMvc.perform(put("/admin/users/ban/999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(containsString("No se ha encontrado")));
    }
}
```

### Repository Testing

**Objetivo**: Verificar queries JPA personalizadas.

**Herramientas**:
- `@DataJpaTest`
- Base de datos H2 en memoria

**Cobertura**:
- Queries personalizadas en `ContactRequestRepository`:
  - `findByClientId`
  - `findByTrainerId`
  - `findByClientIdAndTrainerIdAndStatusIn`

**Ejemplo**:
```java
@DataJpaTest
class ContactRequestRepositoryTest {
    
    @Autowired
    private ContactRequestRepository repository;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    void shouldFindPendingAndAcceptedRequests() {
        // Given
        ClientEntity client = createClient();
        TrainerEntity trainer = createTrainer();
        ContactRequestEntity pending = createRequest(client, trainer, RequestStatus.PENDING);
        ContactRequestEntity accepted = createRequest(client, trainer, RequestStatus.ACCEPTED);
        entityManager.persist(client);
        entityManager.persist(trainer);
        entityManager.persist(pending);
        entityManager.persist(accepted);
        entityManager.flush();
        
        // When
        Optional<ContactRequestEntity> result = repository
            .findByClientIdAndTrainerIdAndStatusIn(
                client.getId(), 
                trainer.getId(), 
                List.of(RequestStatus.PENDING, RequestStatus.ACCEPTED)
            );
        
        // Then
        assertThat(result).isPresent();
    }
}
```

### Test Data Management

**Estrategia**:
- Usar `@Sql` scripts para datos de test complejos
- Usar builders o factory methods para crear entidades en tests
- Limpiar datos entre tests con `@DirtiesContext` o `@Transactional`

**Ejemplo de SQL script** (`test-data/users.sql`):
```sql
INSERT INTO users (id, name, email, password, role, is_premium, is_banned, created_at) 
VALUES 
(1, 'Admin User', 'admin@test.com', '$2a$10$...', 'ADMIN', false, false, NOW()),
(2, 'Client User', 'client@test.com', '$2a$10$...', 'CLIENT', false, false, NOW()),
(3, 'Trainer User', 'trainer@test.com', '$2a$10$...', 'TRAINER', false, false, NOW());
```

### Test Coverage Goals

- **Unit Tests**: 80%+ cobertura de Services
- **Integration Tests**: 100% de endpoints cubiertos
- **Repository Tests**: 100% de queries personalizadas cubiertas

### Running Tests

```bash
# Run all tests
./mvnw test

# Run only unit tests
./mvnw test -Dtest="*Test"

# Run only integration tests
./mvnw test -Dtest="*IntegrationTest"

# Run with coverage report
./mvnw test jacoco:report
```

---

## Implementation Notes

### Security Considerations

1. **Password Encryption**: Usar `PasswordEncoder` (BCrypt) para encriptar contraseñas de nuevos admins
2. **JWT Validation**: Todos los endpoints requieren token JWT válido excepto login/register
3. **Role-Based Access**: Usar `@PreAuthorize` consistentemente en todos los controllers
4. **Input Sanitization**: Validar y sanitizar todos los inputs de usuario

### Performance Considerations

1. **Database Queries**: 
   - Usar `@EntityGraph` o `JOIN FETCH` para evitar N+1 queries
   - Indexar campos frecuentemente buscados (email, role, isBanned)
2. **Pagination**: Considerar paginación para endpoints que devuelven listas grandes
3. **Caching**: Considerar cache para estadísticas de entrenador si se consultan frecuentemente

### Migration Strategy

1. **Database Migration**: Si `isBanned` no existe en `UserEntity`, crear migración Flyway/Liquibase
2. **Backward Compatibility**: Asegurar que cambios no rompan funcionalidad existente
3. **Deployment**: Desplegar en orden: DB migration → Backend → Tests

### Code Quality Standards

1. **Naming Conventions**: Seguir convenciones Java estándar (camelCase, PascalCase)
2. **Documentation**: Javadoc en interfaces públicas y métodos complejos
3. **Code Style**: Seguir Google Java Style Guide
4. **Lombok**: Usar `@RequiredArgsConstructor`, `@Data` para reducir boilerplate

---

## Appendix: Endpoint Summary

### Admin Endpoints

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| PUT | `/admin/users/ban/{id}` | ADMIN | Banear/desbanear usuario |
| POST | `/admin/createAdmin` | ADMIN | Crear nuevo administrador |
| DELETE | `/admin/videos/delete/{id}` | ADMIN | Eliminar vídeo |
| GET | `/admin/users/banned` | ADMIN | Listar usuarios baneados |
| GET | `/admin/users/byRole?role={role}` | ADMIN | Filtrar usuarios por rol |
| PUT | `/admin/users/premium/{id}` | ADMIN | Alternar estado premium |

### Client Endpoints

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/client/contact/request/{trainerId}` | CLIENT | Enviar solicitud de contacto |
| GET | `/client/contact/myRequests` | CLIENT | Ver mis solicitudes enviadas |
| GET | `/client/following` | CLIENT | Ver entrenadores seguidos |

### SharedUsers Endpoints

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| GET | `/profile/{id}` | ANY | Ver perfil de usuario |
| PUT | `/profile/modify` | ANY | Modificar perfil propio |
| DELETE | `/profile/delete` | ANY | Eliminar perfil propio |
| GET | `/search/users?name={name}` | ANY | Buscar usuarios por nombre |
| GET | `/search/videos?category={category}` | ANY | Buscar vídeos por categoría |

### Trainer Endpoints

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| GET | `/trainer/stats` | TRAINER | Ver estadísticas propias |
| GET | `/trainer/requests` | TRAINER | Ver solicitudes recibidas |
| PUT | `/trainer/requests/respond/{requestId}` | TRAINER | Responder solicitud |

