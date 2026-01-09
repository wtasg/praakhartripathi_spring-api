# Workout Tracker API

A secure Spring Boot REST API that enables users to log workouts, exercises, and track fitness progress over time.

## Features

*   **User Authentication**: Secure signup and login using JWT (JSON Web Tokens).
*   **Profile Management**: Update user profile, change password, and reset password.
*   **Workout Management**: Create, read, update, and delete workouts.
*   **Exercise Management**: Add, view, update, and remove exercises within workouts.
*   **Filtering & Stats**: Filter workouts by date, week, or month, and view overall statistics.
*   **Export Data**: Export workout history to CSV and PDF formats.

## Technologies Used

*   **Java 17**
*   **Spring Boot 3.5.9**
*   **Spring Security** (JWT Authentication)
*   **Spring Data JPA** (Hibernate)
*   **PostgreSQL**
*   **Maven**
*   **OpenCSV** (for CSV export)
*   **iText** (for PDF export)

## Getting Started

### Prerequisites

*   Java 17 or higher
*   Maven
*   PostgreSQL database

### Configuration

1.  Create a PostgreSQL database named `workout_tracker_db`.
2.  Update `src/main/resources/application.properties` with your database credentials:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/workout_tracker_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

### Running the Application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## API Endpoints

### Authentication

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/signup` | Register a new user |
| `POST` | `/api/auth/signin` | Login and get JWT token |
| `POST` | `/api/auth/forgot-password` | Reset password (simulated) |
| `POST` | `/api/auth/update-password` | Change current password |
| `GET` | `/api/auth/me` | Get current user profile |
| `PUT` | `/api/auth/profile` | Update user profile |

### Workouts

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/workouts` | Create a new workout |
| `GET` | `/api/workouts` | Get all workouts |
| `GET` | `/api/workouts/{id}` | Get workout by ID |
| `PUT` | `/api/workouts/{id}` | Update workout |
| `DELETE` | `/api/workouts/{id}` | Delete workout |
| `GET` | `/api/workouts/date/{date}` | Get workouts by date (yyyy-mm-dd) |
| `GET` | `/api/workouts/weekly` | Get workouts for the current week |
| `GET` | `/api/workouts/monthly` | Get workouts for the current month |
| `GET` | `/api/workouts/stats` | Get workout statistics |
| `GET` | `/api/workouts/export/csv` | Export workouts to CSV |
| `GET` | `/api/workouts/export/pdf` | Export workouts to PDF |

### Exercises

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/workouts/{workoutId}/exercises` | Add exercise to workout |
| `GET` | `/api/workouts/{workoutId}/exercises` | Get all exercises for a workout |
| `GET` | `/api/workouts/{workoutId}/exercises/{exerciseId}` | Get exercise by ID |
| `PUT` | `/api/workouts/{workoutId}/exercises/{exerciseId}` | Update exercise |
| `DELETE` | `/api/workouts/{workoutId}/exercises/{exerciseId}` | Delete exercise |

## Usage

Most endpoints require a Bearer Token in the `Authorization` header:

```
Authorization: Bearer <your_jwt_token>
```
