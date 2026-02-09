# Markdown Note-Taking Application

A Spring Boot-based REST application enabling users to create, edit, and manage Markdown notes. This application
implements Markdown-to-HTML rendering using CommonMark, RESTful APIs with Spring Data JPA, and secure note storage with
PostgreSQL.

## Features

- **Create Notes**: Save notes with a title and Markdown content.
- **Read Notes**: Retrieve all notes or specific notes by ID.
- **Update Notes**: Modify existing notes.
- **Delete Notes**: Remove notes.
- **Markdown Rendering**: Automatically converts Markdown content to HTML upon saving.

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **Markdown Parser**: CommonMark
- **Tools**: Maven, Lombok

## Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL installed and running

## Configuration

The application is configured to connect to a PostgreSQL database named `note_db`.

Update `src/main/resources/application.properties` if your database credentials differ:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/note_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Running the Application

1. **Clone the repository**
2. **Create the database**:
   ```sql
   CREATE DATABASE note_db;
   ```
3. **Build and Run**:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## API Endpoints

### 1. Get All Notes

- **URL**: `/api/notes`
- **Method**: `GET`

### 2. Get Note by ID

- **URL**: `/api/notes/{id}`
- **Method**: `GET`

### 3. Create a Note

- **URL**: `/api/notes`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "title": "My Note",
    "content": "# Heading\nThis is **bold** text."
  }
  ```

### 4. Update a Note

- **URL**: `/api/notes/{id}`
- **Method**: `PUT`
- **Body**:
  ```json
  {
    "title": "Updated Title",
    "content": "Updated content."
  }
  ```

### 5. Delete a Note

- **URL**: `/api/notes/{id}`
- **Method**: `DELETE`
