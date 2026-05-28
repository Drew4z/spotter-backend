# 🏋️ Spotter — Backend API

**Spotter** es una plataforma social de fitness que conecta entrenadores personales con clientes. Permite a los entrenadores publicar vídeos de entrenamiento y gestionar solicitudes de contacto, mientras que los clientes pueden descubrir entrenadores, seguirlos, dar likes a sus vídeos y solicitar sesiones personalizadas. La plataforma incluye un panel de administración completo para la moderación de contenido y la verificación de entrenadores.

---

## 🏛️ Arquitectura y Patrones

El proyecto implementa **Arquitectura Hexagonal (Ports & Adapters)** con una separación estricta por capas dentro de cada caso de uso:

```
useCases/
└── {feature}/
    ├── application/          # Orquestador + puertos de entrada (interfaces UseCase)
    │   ├── in/               # Puerto de entrada (interfaz)
    │   └── persistence/      # Puerto de salida (interfaz repositorio)
    ├── domain/               # Lógica de negocio pura (sin dependencias de framework)
    └── infrastructure/
        ├── adapter/in/       # Controladores REST (adaptadores de entrada)
        ├── adapter/persistence/ # Implementaciones de repositorio (adaptadores de salida)
        └── DTO/              # Objetos de transferencia de datos
```

**Flujo de datos:**

```
HTTP Request → Controller (Adapter In)
    → Orchestrator (Application Layer)
        → Service (Domain Logic)
            → Repository Port (Interface)
                → Repository Adapter (Infrastructure / JPA)
                    → PostgreSQL
```

**Patrones aplicados:**
- **Hexagonal Architecture** — el dominio no depende de Spring ni de JPA
- **Orchestrator Pattern** — coordinadores delgados en la capa de aplicación
- **Global Exception Handler** — manejo centralizado de errores con respuestas consistentes
- **Table Inheritance (JOINED)** — herencia de entidades JPA para `User → Trainer / Client`
- **Stateless JWT** — autenticación sin estado para escalabilidad horizontal

---

## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
|-----------|-----------|
| **Lenguaje** | Java 21 |
| **Framework** | Spring Boot 4.0.2 |
| **Seguridad** | Spring Security + JWT (JJWT 0.11.5) |
| **ORM** | Spring Data JPA / Hibernate 7 |
| **Base de datos** | PostgreSQL |
| **Documentación API** | SpringDoc OpenAPI 2.8.4 (Swagger UI) |
| **Build** | Apache Maven 3.9.x |
| **Utilidades** | Lombok, Bean Validation |
| **Contenedores** | Docker (multi-stage build) |
| **Servidor** | Apache Tomcat (embebido) |

---

## ✅ Requisitos Previos

Asegúrate de tener instalado en tu máquina:

- **Java 21** (JDK) — [Descargar](https://adoptium.net/) *(solo para ejecución local)*
- **Apache Maven 3.9+** — [Descargar](https://maven.apache.org/download.cgi) *(solo para ejecución local)*
- **Docker y Docker Compose** — [Descargar](https://www.docker.com/products/docker-desktop/) *(recomendado)*
- **Git** — [Descargar](https://git-scm.com/)

---

## 🚀 Instalación y Despliegue

### 1. Clonar el repositorio

```bash
git clone https://github.com/Drew4z/spotter_backend.git
cd spotter_backend
```

### 2. Configurar variables de entorno

Copia el archivo de ejemplo y rellena tus valores:

```bash
cp .env.example .env
```

Edita el `.env` con tus credenciales:

```env
JWT_SECRET_KEY=tu_clave_base64_aqui
DB_PASSWORD=tu_password_aqui
```

> ⚠️ **Importante:** `JWT_SECRET_KEY` debe ser una cadena codificada en Base64 de al menos 256 bits. Puedes generar una con:
> ```bash
> openssl rand -base64 32
> ```

### 3. Opción A — Arrancar con Docker Compose *(recomendado)*

Levanta el backend y la base de datos PostgreSQL con un solo comando:

```bash
docker compose up --build
```

Esto arranca dos servicios:
- **`db`** — PostgreSQL 15 en el puerto `5433`, con volumen persistente
- **`backend`** — API Spring Boot en el puerto `8081`, que espera a que la base de datos esté lista

Para detener los servicios:

```bash
docker compose down
```

Para detener y eliminar también los volúmenes (borra los datos de la BD):

```bash
docker compose down -v
```

### 4. Opción B — Arrancar localmente con Maven

Si prefieres ejecutar el backend sin Docker, necesitarás una instancia de PostgreSQL corriendo localmente con una base de datos llamada `spotter_db`:

```sql
CREATE DATABASE spotter_db;
```

Luego exporta las variables de entorno y arranca la aplicación:

```bash
# Exportar variables (Linux/macOS)
export JWT_SECRET_KEY=tu_clave_base64_aqui
export DB_PASSWORD=tu_password_aqui

mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8081/api`

---

## 📡 Uso y Endpoints

La documentación interactiva completa está disponible en **Swagger UI** una vez arrancada la aplicación:

```
http://localhost:8081/api/swagger-ui.html
```

### Resumen de endpoints principales

#### 🔐 Autenticación — `/auth`

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/auth/register` | Registrar usuario (CLIENT o TRAINER) | ❌ |
| `POST` | `/auth/login` | Iniciar sesión, devuelve JWT (24h) | ❌ |

**Ejemplo de registro:**
```json
POST /api/auth/register
{
  "name": "Carlos López",
  "email": "carlos@example.com",
  "password": "securePass123",
  "role": "TRAINER"
}
```

**Ejemplo de login:**
```json
POST /api/auth/login
{
  "email": "carlos@example.com",
  "password": "securePass123"
}
// Respuesta: { "token": "eyJhbGci..." }
```

> Todas las rutas protegidas requieren el header: `Authorization: Bearer {token}`

---

#### 👤 Perfil — `/profile`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/profile/{id}` | Ver perfil de un usuario |
| `PUT` | `/profile/modify` | Modificar perfil propio |
| `GET` | `/profile/video/get/{id}` | Vídeos de un entrenador |

---

#### 🏃 Cliente — `/client`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/client/video/getFeed` | Feed aleatorio de vídeos (`?limit=5`) |
| `GET` | `/client/video/getFollowingFeed` | Feed de entrenadores seguidos (paginado) |
| `POST` | `/client/video/like/{id}` | Dar/quitar like a un vídeo |
| `GET` | `/client/video/liked` | Vídeos que me gustan |
| `POST` | `/client/followTrainer/{id}` | Seguir/dejar de seguir entrenador |
| `GET` | `/client/following` | Entrenadores que sigo |
| `POST` | `/client/contact/request/{trainerId}` | Enviar solicitud de contacto |

---

#### 🎽 Entrenador — `/trainer`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/trainer/video/upload` | Subir vídeo de entrenamiento |
| `PUT` | `/trainer/video/modify/{id}` | Editar metadatos de un vídeo |
| `DELETE` | `/trainer/video/delete/{id}` | Eliminar vídeo |
| `GET` | `/trainer/contact/requests` | Ver solicitudes de contacto pendientes |
| `PUT` | `/trainer/contact/respond/{requestId}` | Aceptar o rechazar solicitud |

---

#### 🛡️ Administrador — `/admin`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/admin/users` | Listar todos los usuarios |
| `PUT` | `/admin/users/ban/{id}` | Banear/desbanear usuario |
| `PUT` | `/admin/trainers/approve/{id}` | Verificar entrenador |
| `PUT` | `/admin/trainers/reject/{id}` | Rechazar verificación |
| `DELETE` | `/admin/videos/delete/{id}` | Eliminar vídeo inapropiado |
| `POST` | `/admin/createAdmin` | Crear nuevo administrador |

---

#### 🔍 Entrenadores — `/trainers`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/trainers` | Listar todos los entrenadores |
| `GET` | `/trainers/search?name={name}` | Buscar entrenadores por nombre |

---

### Formato de error estándar

Todos los errores devuelven la siguiente estructura:

```json
{
  "message": "Entrenador no encontrado",
  "status": 404,
  "timestamp": "2026-05-19T21:00:00"
}
```

---

## 🗺️ Próximos Pasos (Roadmap)

1. **☁️ Integración con Cloudinary** — Implementar la subida real de vídeos e imágenes de perfil a Cloudinary, sustituyendo las URLs estáticas actuales por URLs generadas dinámicamente tras la carga del archivo.

2. **🔔 Sistema de notificaciones en tiempo real** — Completar el módulo de notificaciones (la estructura ya existe) usando WebSockets o Server-Sent Events para alertar a los entrenadores cuando reciben nuevas solicitudes de contacto o seguidores.

3. **💳 Integración de pagos para Premium** — Conectar el flag `isPremium` con una pasarela de pagos (Stripe o PayPal) para gestionar suscripciones de forma automática, incluyendo webhooks para activar/desactivar el estado premium según el ciclo de facturación.

---

## 👨‍💻 Autor

**Andrew Arauz**

* [LinkedIn](https://www.linkedin.com/in/andrew-steven-arauz-ca%C3%B1arte-150a422b0)
* [GitHub](https://github.com/Drew4z)
