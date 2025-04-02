
## Spring Boot Project Initialization

The project was initialized using [Spring Initializr](https://start.spring.io/) with the following settings:

- **Spring Boot Version**: 3.4.4
- **Project Type**: Maven
- **Language**: Java
- **Java Version**: 17
- **Dependencies**:
    - Spring Web
    - Spring Data JPA
    - PostgreSQL Driver
    - Springdoc OpenAPI (Swagger)


# Project Setup Documentation: User API with Spring Boot, PostgreSQL, Kafka

This document outlines all the steps taken to create the `userapi` project from scratch.

---

## 1. Project Initialization

- **IDE Selection**: Chose IntelliJ IDEA for Java development.
- **Spring Boot Version**: 3.4.4 selected during setup.
- **Project Initialization**:
    - Spring Initializr used.
    - Project name: `userapi`
    - Dependencies added:
        - Spring Web
        - Spring Data JPA
        - PostgreSQL Driver
        - Spring Boot DevTools
        - Springdoc OpenAPI UI (for Swagger)
        - Kafka (added later manually)

---

## 2. PostgreSQL Setup

- **Installed PostgreSQL locally** and created a database:
    - Database name: `userdb`
    - Username: `postgres`
    - Password: `password`
- **pgAdmin installed** for visual management of PostgreSQL.
- **Database created using pgAdmin**.

---

## 3. Spring Boot Configuration

- Created `application.properties` in `src/main/resources`:
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
  spring.datasource.username=postgres
  spring.datasource.password=password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  ```

---

## 4. Creating REST API

- Created:
    - `User` model with UUID primary key.
    - `UserRepository` interface extending JpaRepository.
    - `UserService` and `UserServiceImpl` for business logic.
    - `UserController` with endpoints:
        - `GET /users` - fetch all users.
        - `POST /users` - create a user.
        - `DELETE /users/{id}` - delete a user by ID.

---

## 5. Swagger Integration

- Added Swagger UI:
    - Dependency: `springdoc-openapi-starter-webmvc-ui`
    - Swagger is accessible at: `http://localhost:8080/swagger-ui.html`

---

## 6. Kafka Setup with Docker

- **Used Docker Compose** to run Kafka and Zookeeper:
    - File: `docker-compose.yml`
  ```yaml
  version: '3'
  services:
    zookeeper:
      image: confluentinc/cp-zookeeper:7.5.1
      ports:
        - "2181:2181"
      environment:
        ZOOKEEPER_CLIENT_PORT: 2181
        ZOOKEEPER_TICK_TIME: 2000

    kafka:
      image: confluentinc/cp-kafka:7.5.1
      ports:
        - "9092:9092"
      environment:
        KAFKA_BROKER_ID: 1
        KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
        KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
        KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
  ```

- **Kafka Integration**:
    - Added `KafkaProducer`, `KafkaConsumer`, and `KafkaMessageStore`.
    - On user creation, a Kafka message is produced.
    - Added new endpoint to view Kafka messages:
        - `GET /kafka/messages`

---

## 7. Unit Testing

- **UserServiceImplTest**:
    - `testGetAllUsers_ReturnsListOfUsers`
    - `testCreateUser_SavesAndReturnsUser`
    - `testDeleteUserById_DeletesUserSuccessfully`
- **KafkaProducerTest**:
    - Verifies messages are sent and stored correctly using mock.

---

## 8. Final Touches

- Cleaned up project structure:
    - Added service layer for separation of concerns.
- Added proper `README.md`.
- All working endpoints available in Swagger.

---

## Running the Project

1. Start Docker:
   ```
   docker-compose up
   ```
2. Run Spring Boot project from IntelliJ or terminal:
   ```
   ./mvnw spring-boot:run
   ```
3. Access Swagger at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Project Summary

A full-stack backend Spring Boot application with PostgreSQL and Kafka integration, supporting:
- RESTful User Management
- Kafka Event Publishing and Viewing
- Full Swagger Documentation
- Unit Testing with JUnit and Mockito
