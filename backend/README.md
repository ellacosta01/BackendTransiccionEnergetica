# Backend Transición Energética

API REST desarrollada con **Spring Boot y PostgreSQL** para consultar información sobre generación de energía renovable en diferentes países y regiones.

El sistema también incluye **gestión de usuarios, autenticación con JWT y control de acceso por roles** para proteger los endpoints.

Este proyecto fue realizado como práctica de backend aplicando arquitectura por capas, consultas SQL analíticas y seguridad básica con Spring Security.

---

# Tecnologías utilizadas

- Java
- Spring Boot
- Spring Security
- JWT (Json Web Token)
- PostgreSQL
- Spring Data JPA
- Swagger / OpenAPI
- Maven

---

# Arquitectura del proyecto

El proyecto sigue una arquitectura por capas para mantener el código organizado y separar responsabilidades.

## Estructura del proyecto

```
src/main/java/com/energy/demo

│
├── config
│   ├── SecurityConfig.java
│   └── OpenApiConfig.java
│
├── controller
│   ├── AppUserController.java
│   ├── AuthController.java
│   ├── EnergyController.java
│   └── HomeController.java
│
├── controller/dto
│   ├── ApiResponse.java
│   ├── CreateUserRequest.java
│   ├── UpdateUserRequest.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   └── UserResponse.java
│
├── exception
│   └── GlobalExceptionHandler.java
│
├── model
│   ├── AppUser.java
│   └── Role.java
│
├── repository
│   ├── AppUserRepository.java
│   └── EnergyRepository.java
│
├── security
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthFilter.java
│   └── JwtService.java
│
├── service
│   ├── AppUserService.java
│   ├── AuthService.java
│   └── EnergyService.java
│
└── DemoApplication.java
```

Cada capa tiene una responsabilidad clara:

config  
Configuraciones generales del sistema como seguridad y Swagger.

controller  
Contiene los endpoints REST que exponen la funcionalidad de la API.

dto  
Objetos que se utilizan para enviar o recibir información entre cliente y servidor.

exception  
Manejo global de errores.

model  
Entidades del sistema que representan tablas de la base de datos.

repository  
Acceso a base de datos y consultas SQL.

security  
Manejo de autenticación y validación del token JWT.

service  
Contiene la lógica de negocio de la aplicación.

---

# Autenticación y seguridad

La aplicación utiliza **JWT (Json Web Token)** para proteger los endpoints.

Flujo básico:

1. El usuario inicia sesión con sus credenciales.
2. El sistema genera un token JWT.
3. El cliente envía el token en las siguientes peticiones.

Ejemplo de header:

```
Authorization: Bearer TOKEN_JWT
```

---

# Roles del sistema

| Rol | Permisos |
|----|----|
| ADMIN | Acceso completo al sistema |
| USER | Solo lectura de información |

---

# Endpoints principales

## Autenticación

### Login

```
POST /api/auth/login
```

Ejemplo de body:

```json
{
  "email": "admin@correo.com",
  "password": "123456"
}
```

Respuesta:

```json
{
  "token": "jwt-token",
  "type": "Bearer",
  "email": "admin@correo.com",
  "role": "ADMIN"
}
```

---

# Gestión de usuarios

| Método | Endpoint | Descripción |
|------|------|------|
| POST | /api/users | Crear usuario |
| GET | /api/users | Listar usuarios |
| GET | /api/users/{id} | Obtener usuario |
| PUT | /api/users/{id} | Actualizar usuario |
| DELETE | /api/users/{id} | Eliminar usuario |

---

# Consultas energéticas

La API incluye **5 consultas principales sobre generación energética**.

### 1. Producción por fuente energética

```
GET /api/energy/production-by-source?year=2021
```

Muestra la generación total por fuente energética en cada región.

---

### 2. Participación renovable por región

```
GET /api/energy/renewable-share-by-region?year=2021
```

Muestra el porcentaje de electricidad renovable por región.

---

### 3. Tendencia de generación por país y fuente

```
GET /api/energy/trend?country=Germany&source=Wind
```

Permite analizar cómo cambia la generación de una fuente energética a lo largo del tiempo.

---

### 4. Top países productores por fuente

```
GET /api/energy/top-producers?source=Solar&year=2021&limit=10
```

Lista los países con mayor producción para una fuente energética.

---

### 5. Participación global por fuente

```
GET /api/energy/global-share?year=2021
```

Calcula el porcentaje que representa cada fuente energética en la generación global.

---

# Documentación de la API

Swagger permite probar los endpoints directamente desde el navegador.

```
http://localhost:8083/swagger-ui/index.html
```

Desde Swagger también se puede autenticar usando el token JWT.

---

# Configuración de base de datos

El proyecto utiliza **PostgreSQL**.

Ejemplo de configuración en `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/energy_app
spring.datasource.username=ella
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
```

---

# Cómo ejecutar el proyecto

1. Clonar el repositorio

```
git clone REPOSITORIO
```

2. Entrar al proyecto

```
cd BackendTransicionEnergetica
```

3. Ejecutar la aplicación

```
mvn spring-boot:run
```

La API quedará disponible en:

```
http://localhost:8083
```

---

# Autor

Ella Acosta - Proyecto académico desarrollado como práctica de backend utilizando **Spring Boot, PostgreSQL y APIs REST**.