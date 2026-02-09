# User Authentication Service

A robust User Authentication Service built with Spring Boot, providing secure user registration, login, and password
management functionalities using JWT (JSON Web Tokens).

## Features

* **User Registration**: Create new user accounts with username and password.
* **User Login**: Authenticate users and issue JWT tokens for secure access.
* **Forgot Password**: Reset user password using username.
* **Update Password**: Change existing password by verifying the current one.
* **JWT Authentication**: Secure API endpoints using JSON Web Tokens.
* **Role-Based Access**: Supports user roles (e.g., USER, ADMIN).

## Technologies Used

* **Java 17**
* **Spring Boot 3.2.5**
* **Spring Security**
* **Spring Data JPA**
* **PostgreSQL**
* **JWT (jjwt)**
* **Maven**

## Getting Started

### Prerequisites

* Java 17 or higher
* Maven
* PostgreSQL database

### Configuration

1. **Database Setup**: Ensure you have a PostgreSQL database running.
2. **Application Properties**: Update `src/main/resources/application.properties` with your database credentials.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/auth_service
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

### Build and Run

1. **Build the project**:

   ```sh
   ./mvnw clean install
   ```

2. **Run the application**:

   ```sh
   ./mvnw spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

## API Endpoints

### Authentication

| Method | Endpoint                    | Description             | Request Body                                                                   |
|:-------|:----------------------------|:------------------------|:-------------------------------------------------------------------------------|
| `POST` | `/api/auth/register`        | Register a new user     | `{"username": "user", "password": "password"}`                                 |
| `POST` | `/api/auth/login`           | Login and get JWT token | `{"username": "user", "password": "password"}`                                 |
| `POST` | `/api/auth/forgot-password` | Reset password          | `{"username": "user", "newPassword": "newPass"}`                               |
| `POST` | `/api/auth/update-password` | Update password         | `{"username": "user", "currentPassword": "oldPass", "newPassword": "newPass"}` |

## Testing with cURL

**Register:**

```sh
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

**Login:**

```sh
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

**Forgot Password:**

```sh
curl -X POST http://localhost:8080/api/auth/forgot-password \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "newPassword": "newpassword123"}'
```

**Update Password:**

```sh
curl -X POST http://localhost:8080/api/auth/update-password \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "currentPassword": "newpassword123", "newPassword": "newerpassword456"}'
```
