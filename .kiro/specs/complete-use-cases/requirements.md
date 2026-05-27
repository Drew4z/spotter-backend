# Documento de Requisitos

## Introducción

Este documento describe los requisitos funcionales para completar los casos de uso pendientes del backend del TFG **Spotter**, una plataforma que conecta clientes con entrenadores personales. El backend está desarrollado con Java 21 y Spring Boot 4, siguiendo una arquitectura de puertos y adaptadores (hexagonal).

Los casos de uso a implementar cubren las áreas de administración, gestión de perfil de usuario, interacción cliente-entrenador, filtros de búsqueda y estadísticas de entrenador. Todos deben seguir el patrón arquitectónico ya establecido en el proyecto: `Controller → UseCase (interfaz) → Orchestrator → Service → RepositoryPort → RepositoryAdapter`.

---

## Glosario

- **System**: El backend de Spotter (Spring Boot API REST).
- **Admin**: Usuario con rol `ADMIN`. Gestiona la plataforma.
- **Client**: Usuario con rol `CLIENT`. Consume contenido y contacta entrenadores.
- **Trainer**: Usuario con rol `TRAINER`. Publica vídeos y gestiona solicitudes de contacto.
- **UserEntity**: Entidad JPA base de la que heredan `ClientEntity` y `TrainerEntity`.
- **ContactRequestEntity**: Entidad que representa una solicitud de contacto de un cliente a un entrenador, con estado `PENDING`, `ACCEPTED` o `REJECTED`.
- **FollowEntity**: Entidad que representa la relación de seguimiento entre un cliente y un entrenador.
- **VideoEntity**: Entidad que representa un vídeo subido por un entrenador.
- **Orchestrator**: Clase que implementa la interfaz `UseCase` y delega en el `Service`.
- **Service**: Clase de dominio que contiene la lógica de negocio y usa el `RepositoryPort`.
- **RepositoryPort**: Interfaz de salida que abstrae el acceso a datos.
- **RepositoryAdapter**: Implementación del `RepositoryPort` que usa los repositorios JPA compartidos.
- **Utils**: Clase de utilidad compartida que extrae el usuario autenticado del token JWT.
- **Mapper**: Clase de mapeo compartida que convierte entre entidades, DTOs y modelos de dominio.
- **isBanned**: Campo booleano en `UserEntity` que indica si un usuario está baneado.
- **TrainerRepository**: Repositorio JPA compartido para `TrainerEntity`.
- **UserRepository**: Repositorio JPA compartido para `UserEntity`.
- **FollowRepository**: Repositorio JPA compartido para `FollowEntity`.
- **VideoRepository**: Repositorio JPA compartido para `VideoEntity`.

---

## Requisitos

---

### Requisito 1: Banear y desbanear usuarios (Admin)

**User Story:** Como administrador, quiero poder banear y desbanear usuarios, para poder moderar la plataforma y retirar el acceso a usuarios que incumplan las normas.

#### Criterios de Aceptación

1. WHEN un Admin realiza una petición `PUT /admin/users/ban/{id}`, THE System SHALL localizar al usuario por su ID y alternar su estado de baneo (si está baneado, lo desbanea; si no, lo banea).
2. IF el usuario con el ID proporcionado no existe en la base de datos, THEN THE System SHALL lanzar una `ResourceNotFoundException` con el mensaje "No se ha encontrado al usuario".
3. WHEN el estado de baneo se actualiza correctamente, THE System SHALL devolver un `String` con el mensaje de confirmación de la acción realizada.
4. THE System SHALL proteger el endpoint `PUT /admin/users/ban/{id}` con la anotación `@PreAuthorize("hasAuthority('ADMIN')")`.

---

### Requisito 2: Crear administradores (Admin)

**User Story:** Como administrador, quiero poder crear nuevas cuentas de administrador, para poder delegar tareas de moderación a otros usuarios de confianza.

#### Criterios de Aceptación

1. WHEN un Admin realiza una petición `POST /admin/createAdmin` con un cuerpo JSON que contiene `name`, `email` y `password`, THE System SHALL crear un nuevo usuario con rol `ADMIN` y devolverlo como `UserResponse`.
2. IF el email proporcionado ya existe en la base de datos, THEN THE System SHALL lanzar una `DuplicateActionException` con el mensaje "El email ya está en uso".
3. THE System SHALL encriptar la contraseña del nuevo administrador usando el `PasswordEncoder` antes de persistirla.
4. THE System SHALL proteger el endpoint `POST /admin/createAdmin` con la anotación `@PreAuthorize("hasAuthority('ADMIN')")`.

---

### Requisito 3: Eliminar vídeos inapropiados (Admin)

**User Story:** Como administrador, quiero poder eliminar cualquier vídeo de la plataforma, para poder retirar contenido inapropiado independientemente de quién sea su autor.

#### Criterios de Aceptación

1. WHEN un Admin realiza una petición `DELETE /admin/videos/delete/{id}`, THE System SHALL eliminar el vídeo con el ID proporcionado de la base de datos.
2. IF el vídeo con el ID proporcionado no existe, THEN THE System SHALL lanzar una `ResourceNotFoundException` con el mensaje "No se ha encontrado el vídeo".
3. WHEN el vídeo se elimina correctamente, THE System SHALL devolver un `String` con el mensaje de confirmación.
4. THE System SHALL proteger el endpoint `DELETE /admin/videos/delete/{id}` con la anotación `@PreAuthorize("hasAuthority('ADMIN')")`.

---

### Requisito 4: Filtrar usuarios baneados (Admin)

**User Story:** Como administrador, quiero poder ver la lista de usuarios baneados, para tener una visión clara del estado de moderación de la plataforma.

#### Criterios de Aceptación

1. WHEN un Admin realiza una petición `GET /admin/users/banned`, THE System SHALL devolver una lista de `UserResponse` que contenga únicamente los usuarios cuyo campo `isBanned` sea `true`.
2. IF no hay usuarios baneados, THE System SHALL devolver una lista vacía.
3. THE System SHALL proteger el endpoint `GET /admin/users/banned` con la anotación `@PreAuthorize("hasAuthority('ADMIN')")`.

---

### Requisito 5: Filtrar usuarios por categoría/rol (Admin)

**User Story:** Como administrador, quiero poder filtrar usuarios por su rol (CLIENT o TRAINER), para gestionar cada tipo de usuario de forma independiente.

#### Criterios de Aceptación

1. WHEN un Admin realiza una petición `GET /admin/users/byRole?role={role}`, THE System SHALL devolver una lista de `UserResponse` que contenga únicamente los usuarios cuyo campo `role` coincida con el valor del parámetro.
2. IF no hay usuarios con ese rol, THE System SHALL devolver una lista vacía.
3. THE System SHALL proteger el endpoint `GET /admin/users/byRole` con la anotación `@PreAuthorize("hasAuthority('ADMIN')")`.

---

### Requisito 6: Gestionar categoría/premium de usuario (Admin)

**User Story:** Como administrador, quiero poder cambiar el estado premium de un usuario, para gestionar manualmente las suscripciones de la plataforma.

#### Criterios de Aceptación

1. WHEN un Admin realiza una petición `PUT /admin/users/premium/{id}`, THE System SHALL localizar al usuario por su ID y alternar su estado `isPremium` (si es premium, lo quita; si no, lo activa).
2. IF el usuario con el ID proporcionado no existe, THEN THE System SHALL lanzar una `ResourceNotFoundException` con el mensaje "No se ha encontrado al usuario".
3. WHEN el estado premium se actualiza correctamente, THE System SHALL devolver un `String` con el mensaje de confirmación.
4. THE System SHALL proteger el endpoint `PUT /admin/users/premium/{id}` con la anotación `@PreAuthorize("hasAuthority('ADMIN')")`.

---

### Requisito 7: Solicitar contacto con un entrenador (Client)

**User Story:** Como cliente, quiero poder enviar una solicitud de contacto a un entrenador, para poder contratarle o pedirle información sobre sus servicios.

#### Criterios de Aceptación

1. WHEN un Client autenticado realiza una petición `POST /client/contact/request/{trainerId}` con un cuerpo JSON que contiene un campo `message`, THE System SHALL crear una nueva `ContactRequestEntity` con estado `PENDING` y persistirla.
2. IF el entrenador con el ID proporcionado no existe, THEN THE System SHALL lanzar una `ResourceNotFoundException` con el mensaje "No se ha encontrado al entrenador".
3. IF el cliente ya tiene una solicitud `PENDING` o `ACCEPTED` con ese entrenador, THEN THE System SHALL lanzar una `DuplicateActionException` con el mensaje "Ya tienes una solicitud activa con este entrenador".
4. WHEN la solicitud se crea correctamente, THE System SHALL devolver un `String` con el mensaje de confirmación.

---

### Requisito 8: Ver mis solicitudes de contacto (Client)

**User Story:** Como cliente, quiero poder ver todas mis solicitudes de contacto enviadas, para hacer seguimiento del estado de cada una.

#### Criterios de Aceptación

1. WHEN un Client autenticado realiza una petición `GET /client/contact/myRequests`, THE System SHALL devolver una lista de `ContactRequestResponse` con todas las solicitudes enviadas por el cliente autenticado.
2. IF el cliente no tiene solicitudes, THE System SHALL devolver una lista vacía.
3. THE System SHALL incluir en cada `ContactRequestResponse` el ID de la solicitud, el nombre del entrenador, el mensaje enviado, el estado (`PENDING`, `ACCEPTED`, `REJECTED`) y la fecha de creación.

---

### Requisito 9: Ver perfil de usuario (SharedUsers)

**User Story:** Como usuario autenticado (cliente o entrenador), quiero poder ver el perfil público de cualquier usuario, para conocer su información básica.

#### Criterios de Aceptación

1. WHEN un usuario autenticado realiza una petición `GET /profile/{id}`, THE System SHALL devolver un `UserResponse` con los datos del usuario cuyo ID coincida.
2. IF el usuario con el ID proporcionado no existe, THEN THE System SHALL lanzar una `ResourceNotFoundException` con el mensaje "No se ha encontrado al usuario".

---

### Requisito 10: Modificar perfil propio (SharedUsers)

**User Story:** Como usuario autenticado, quiero poder actualizar mi nombre y mi avatar, para mantener mi perfil actualizado.

#### Criterios de Aceptación

1. WHEN un usuario autenticado realiza una petición `PUT /profile/modify` con un cuerpo JSON que contiene `name` y/o `pathAvatar`, THE System SHALL actualizar los campos correspondientes del usuario autenticado y devolver un `UserResponse` con los datos actualizados.
2. IF el token JWT no es válido o no está presente, THEN THE System SHALL devolver un error de autenticación `401`.

---

### Requisito 11: Eliminar perfil propio (SharedUsers)

**User Story:** Como usuario autenticado, quiero poder eliminar mi propia cuenta, para ejercer mi derecho al olvido y retirar mis datos de la plataforma.

#### Criterios de Aceptación

1. WHEN un usuario autenticado realiza una petición `DELETE /profile/delete`, THE System SHALL eliminar la cuenta del usuario autenticado de la base de datos.
2. WHEN la cuenta se elimina correctamente, THE System SHALL devolver un `String` con el mensaje de confirmación.

---

### Requisito 12: Buscar usuario por nombre (SharedUsers)

**User Story:** Como usuario autenticado, quiero poder buscar usuarios por su nombre, para encontrar entrenadores o clientes específicos.

#### Criterios de Aceptación

1. WHEN un usuario autenticado realiza una petición `GET /search/users?name={name}`, THE System SHALL devolver una lista de `UserResponse` cuyos campos `name` contengan el texto buscado (búsqueda insensible a mayúsculas).
2. IF no se encuentran usuarios con ese nombre, THE System SHALL devolver una lista vacía.

---

### Requisito 13: Buscar vídeos por categoría (SharedUsers)

**User Story:** Como usuario autenticado, quiero poder filtrar vídeos por categoría, para encontrar contenido relevante a mis intereses.

#### Criterios de Aceptación

1. WHEN un usuario autenticado realiza una petición `GET /search/videos?category={category}`, THE System SHALL devolver una lista de `VideoResponse` cuyos campos `category` coincidan con el valor del parámetro.
2. IF no hay vídeos en esa categoría, THE System SHALL devolver una lista vacía.

---

### Requisito 14: Ver entrenadores seguidos (SharedUsers)

**User Story:** Como cliente autenticado, quiero poder ver la lista de entrenadores a los que sigo, para gestionar mis seguimientos.

#### Criterios de Aceptación

1. WHEN un Client autenticado realiza una petición `GET /client/following`, THE System SHALL devolver una lista de `UserResponse` con los entrenadores a los que sigue el cliente autenticado.
2. IF el cliente no sigue a ningún entrenador, THE System SHALL devolver una lista vacía.

---

### Requisito 15: Ver estadísticas del entrenador (Trainer)

**User Story:** Como entrenador autenticado, quiero poder ver mis estadísticas, para conocer el alcance de mi contenido en la plataforma.

#### Criterios de Aceptación

1. WHEN un Trainer autenticado realiza una petición `GET /trainer/stats`, THE System SHALL devolver un objeto `TrainerStatsResponse` que contenga: número total de seguidores, número total de vídeos publicados y número total de likes recibidos en todos sus vídeos.
2. IF el entrenador no tiene seguidores, vídeos o likes, THE System SHALL devolver los valores correspondientes a `0`.

---

### Requisito 16: Gestionar solicitudes de contacto recibidas (Trainer)

**User Story:** Como entrenador autenticado, quiero poder ver y responder a las solicitudes de contacto que recibo, para decidir con qué clientes quiero trabajar.

#### Criterios de Aceptación

1. WHEN un Trainer autenticado realiza una petición `GET /trainer/requests`, THE System SHALL devolver una lista de `ContactRequestResponse` con todas las solicitudes recibidas por el entrenador autenticado.
2. WHEN un Trainer autenticado realiza una petición `PUT /trainer/requests/respond/{requestId}` con un cuerpo JSON que contiene un campo `status` con valor `ACCEPTED` o `REJECTED`, THE System SHALL actualizar el estado de la `ContactRequestEntity` correspondiente.
3. IF la solicitud con el ID proporcionado no existe, THEN THE System SHALL lanzar una `ResourceNotFoundException` con el mensaje "No se ha encontrado la solicitud".
4. IF el entrenador autenticado no es el destinatario de la solicitud, THEN THE System SHALL lanzar una `UnauthorizedActionException` con el mensaje "No tienes permiso para responder esta solicitud".
5. WHEN la solicitud se actualiza correctamente, THE System SHALL devolver un `String` con el mensaje de confirmación.
