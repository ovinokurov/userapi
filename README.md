# User API with Kafka Integration

This is a Spring Boot-based RESTful API for managing users. It integrates with Apache Kafka to publish user-related events.

## Features

- Create and retrieve users using a RESTful API
- Kafka integration to send user creation events
- In-memory message storage for consumed Kafka messages
- Swagger UI for API exploration
- Unit tests using JUnit and Mockito
- Dockerized Kafka + Zookeeper setup for local development

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL (optional: currently not integrated)
- Apache Kafka
- Docker / Docker Compose
- Swagger UI (via Springdoc OpenAPI)
- JUnit 5, Mockito

---

## Project Structure

```
userapi/
├── src/
│   ├── main/
│   │   ├── java/com/example/userapi/
│   │   │   ├── controller/       # REST Controllers
│   │   │   ├── kafka/            # Kafka Producer/Consumer/Storage
│   │   │   ├── model/            # Data Models (User)
│   │   │   ├── repository/       # Spring JPA Repositories
│   │   │   └── service/          # Service layer (User + KafkaMessage)
│   ├── test/                     # Unit tests
├── docker-compose.yml           # Kafka + Zookeeper setup
├── pom.xml                      # Maven build file
└── README.md                    # Project info
```

---

## API Endpoints

Once the application is running, visit:

📚 **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### UserController
- `GET /users` — Get all users
- `POST /users` — Create a user
- `DELETE /users/{id}` — Delete a user by ID

### KafkaMessageController
- `GET /kafka/messages` — Get all Kafka messages received

---

## Running the Project with Docker Compose

This project uses Kafka and Zookeeper locally via Docker.

### Step 1: Start Kafka + Zookeeper

```bash
docker-compose up -d
```

This will start:
- `zookeeper` on port **2181**
- `kafka` on port **9092**

### Step 2: Run the Spring Boot App

You can run it in IntelliJ or from the command line:

```bash
./mvnw spring-boot:run
```

Or build a JAR and run:

```bash
./mvnw clean package
java -jar target/userapi-0.0.1-SNAPSHOT.jar
```

---

## Running Unit Tests

```bash
./mvnw test
```

---

## Notes

- Kafka messages are stored in-memory using `KafkaMessageStore`.
- On application restart, old Kafka messages will be gone unless a persistent Kafka topic storage is configured.

---

## Author

Built by Oleg Vinokurov as part of a learning and integration exercise with Spring Boot + Kafka.
