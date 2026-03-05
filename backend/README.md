# Backend Transicion Energetica

API REST desarrollada con Spring Boot para analizar datos de generación eléctrica y participación de energías renovables a partir de un dataset energético.

El sistema permite consultar producción energética por fuente, tendencias por país, participación global de energías y gestionar usuarios.

--------------------------------------------------

TECNOLOGIAS UTILIZADAS

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger / OpenAPI

--------------------------------------------------

ESTRUCTURA DEL PROYECTO

BackendTransicionEnergetica
│
├── README.md
│
└── backend
    └── demo
        ├── pom.xml
        └── src
            ├── main
            │   ├── java
            │   │   └── com.energy.demo
            │   │        ├── controller
            │   │        ├── service
            │   │        ├── repository
            │   │        └── model
            │   │
            │   └── resources
            │        └── application.properties
            │
            └── test

--------------------------------------------------

REQUISITOS

Antes de ejecutar el proyecto debes tener instalado:

- Java 17 o superior
- Maven
- PostgreSQL
- Git

Puedes verificar las versiones con:

java -version
mvn -version

--------------------------------------------------

CONFIGURACION DE BASE DE DATOS

Editar el archivo:

src/main/resources/application.properties

Ejemplo de configuración:

spring.datasource.url=jdbc:postgresql://localhost:5432/energy_db
spring.datasource.username=postgres
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Antes de ejecutar el backend debes crear la base de datos:

CREATE DATABASE energy_db;

--------------------------------------------------

COMO EJECUTAR EL PROYECTO

Ir a la carpeta del backend:

cd backend/demo

Compilar el proyecto:

mvn clean install

Ejecutar la aplicación:

mvn spring-boot:run

Si todo está correcto verás en la consola algo similar a:

Tomcat started on port 8080
Started DemoApplication

--------------------------------------------------

DOCUMENTACION DE LA API

Una vez el backend esté ejecutándose puedes acceder a Swagger en:

http://localhost:8080/swagger-ui/index.html

Desde esta interfaz puedes probar todos los endpoints de la API.

--------------------------------------------------

ENDPOINTS PRINCIPALES

Producción energética por fuente

GET /api/energy/production-by-source?year=2021


Participación de renovables por región

GET /api/energy/renewable-share-by-region?year=2021


Tendencia de energía por país y fuente

GET /api/energy/trend?country=Colombia&source=Solar


Top países productores por fuente

GET /api/energy/top-producers?source=Wind&year=2021&limit=10


Participación global por fuente energética

GET /api/energy/global-share?year=2021

--------------------------------------------------

GESTION DE USUARIOS

CRUD disponible en:

/api/users

Operaciones disponibles:

- Crear usuario
- Listar usuarios
- Obtener usuario por ID
- Actualizar usuario
- Eliminar usuario

--------------------------------------------------

EJEMPLO DE RESPUESTA JSON

Consulta:

GET /api/energy/global-share?year=2021

Respuesta:

[
  {
    "source": "Hydro",
    "total_generation_twh": 4200,
    "percentage_share": 29.5
  },
  {
    "source": "Coal",
    "total_generation_twh": 3800,
    "percentage_share": 26.7
  }
]

--------------------------------------------------

AUTOR

Proyecto desarrollado por Ella Acosta como parte de un proyecto académico de análisis de datos energéticos utilizando Spring Boot.
git add README.md
git commit -m "docs: add project README"
git push