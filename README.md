# User API with Kafka and GraphQL Integration

This is a Spring Boot-based API for managing users, featuring both REST and GraphQL endpoints, and integrating with Apache Kafka to publish user-related events.

## Features

- Create and retrieve users using RESTful API
- Query users with flexible selection of fields using GraphQL
- Kafka integration to send user creation events
- In-memory message storage for consumed Kafka messages
- Swagger UI for REST API exploration
- Unit tests using JUnit and Mockito
- Dockerized Kafka + Zookeeper setup for local development

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL (optional: currently not integrated)
- Apache Kafka
- GraphQL (via Spring Boot Starter GraphQL)
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
│   │   │   ├── graphql/          # GraphQL Query Resolvers
│   │   │   ├── kafka/            # Kafka Producer/Consumer/Storage
│   │   │   ├── model/            # Data Models (User)
│   │   │   ├── repository/       # Spring JPA Repositories
│   │   │   └── service/          # Service layer (User + KafkaMessage)
│   ├── resources/
│   │   ├── application.properties
│   │   └── graphql/
│   │       └── schema.graphqls   # GraphQL schema definition
│   ├── test/                     # Unit tests
├── docker-compose.yml           # Kafka + Zookeeper setup
├── pom.xml                      # Maven build file
└── README.md                    # Project info
```

---

## API Endpoints

Once the application is running, visit:

📚 **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### REST Endpoints

#### UserController
- `GET /users` — Get all users
- `POST /users` — Create a user
- `DELETE /users/{id}` — Delete a user by ID

#### KafkaMessageController
- `GET /kafka/messages` — Get all Kafka messages received

---

## GraphQL Integration

This project includes a GraphQL API using Spring Boot Starter GraphQL for flexible data querying.

### What is GraphQL?
GraphQL is a query language for APIs that allows clients to request exactly the data they need, making APIs more efficient and flexible compared to REST.

### Integration Details
- **Dependency:** `spring-boot-starter-graphql` in `pom.xml`
- **Schema Location:** `src/main/resources/graphql/schema.graphqls`
- **Endpoint:** `http://localhost:8080/graphql`
- **Resolvers:** Implemented in `src/main/java/com/example/userapi/graphql/`

### Example Query
Fetch all users:
```graphql
query {
  users {
    id
    firstName
    lastName
  }
}
```

### Example Mutation (if implemented)
```graphql
mutation {
  createUser(input: { firstName: "John", lastName: "Doe" }) {
    id
    firstName
    lastName
  }
}
```

### Search Users with GraphQL

You can search for users by ID, first name, last name, or both first and last name using the following queries:

#### 1. Search by ID
**Altair/GraphQL Playground:**
```graphql
query {
  user(id: "<USER_ID>") {
    id
    firstName
    lastName
  }
}
```
**Postman (JSON body):**
```json
{
  "query": "{ user(id: \"<USER_ID>\") { id firstName lastName } }"
}
```

#### 2. Search by First and Last Name
**Altair/GraphQL Playground:**
```graphql
query {
  userByName(firstName: "Oleg", lastName: "Vinokurov") {
    id
    firstName
    lastName
  }
}
```
**Postman (JSON body):**
```json
{
  "query": "{ userByName(firstName: \"Oleg\", lastName: \"Vinokurov\") { id firstName lastName } }"
}
```

#### 3. Search by First Name Only
**Altair/GraphQL Playground:**
```graphql
query {
  usersByFirstName(firstName: "Oleg") {
    id
    firstName
    lastName
  }
}
```
**Postman (JSON body):**
```json
{
  "query": "{ usersByFirstName(firstName: \"Oleg\") { id firstName lastName } }"
}
```

#### 4. Search by Last Name Only
**Altair/GraphQL Playground:**
```graphql
query {
  usersByLastName(lastName: "Vinokurov") {
    id
    firstName
    lastName
  }
}
```
**Postman (JSON body):**
```json
{
  "query": "{ usersByLastName(lastName: \"Vinokurov\") { id firstName lastName } }"
}
```

### How to Test
- **Altair/GraphQL Playground:** Paste the query in the editor and execute.
- **Postman:** Set method to POST, URL to `http://localhost:8080/graphql`, and paste the JSON body above.

### Note
Spring Boot 3.x does not provide a built-in GraphQL UI like Swagger. Use external tools for testing and exploration.

---

## Kafka Integration

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

Built by Oleg Vinokurov as part of a learning and integration exercise with Spring Boot, GraphQL, and Kafka.
