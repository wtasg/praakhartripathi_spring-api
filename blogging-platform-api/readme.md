---

# Blog Platform REST API

A Blog Platform REST API built using Spring Boot, Spring Security (JWT), and JPA/Hibernate.
This backend application allows users to register, authenticate, create blogs, like posts, comment, and organize blogs by categories.

---

## Features

### Authentication and User Management

* User registration and login
* JWT-based authentication
* View logged-in user profile
* Fetch user profile by ID

### Blog Management

* Create, update, and delete blogs (authorized users only)
* Pagination and sorting support
* Like blogs
* Get total likes count
* Fetch blogs by user

### Category Management

* Create blog categories
* Fetch all categories
* Fetch blogs by category

### Comment System

* Add comments to blogs
* View comments for a blog
* Delete comments (authorized users only)

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security with JWT
* Spring Data JPA (Hibernate)
* MySQL
* Jakarta Validation
* Maven

---

## Authentication Flow

1. User registers
2. User logs in
3. API returns a JWT token
4. Token must be passed in the Authorization header for secured APIs

```
Authorization: Bearer <JWT_TOKEN>
```

---

## API Endpoints

---

### Auth Controller (`/api/auth`)

| Method | Endpoint    | Description                |
|--------|-------------|----------------------------|
| POST   | `/register` | Register a new user        |
| POST   | `/login`    | Login user and receive JWT |
| GET    | `/me`       | Get logged-in user profile |
| GET    | `/{userId}` | Get user profile by ID     |

---

### Blog Controller (`/api/blogs`)

| Method | Endpoint                | Description                               |
|--------|-------------------------|-------------------------------------------|
| POST   | `/create`               | Create a new blog                         |
| GET    | `/getAllBlogs`          | Get all blogs with pagination and sorting |
| GET    | `/{blogId}`             | Get blog by ID                            |
| PUT    | `/{blogId}`             | Update blog                               |
| DELETE | `/{blogId}`             | Delete blog                               |
| GET    | `/user/{userId}`        | Get blogs by user                         |
| POST   | `/{blogId}/like`        | Like a blog                               |
| GET    | `/{blogId}/likes/count` | Get blog like count                       |

---

### Category Controller (`/api/categories`)

| Method | Endpoint              | Description           |
|--------|-----------------------|-----------------------|
| POST   | `/create`             | Create category       |
| GET    | `/all`                | Get all categories    |
| GET    | `/{categoryId}/blogs` | Get blogs by category |

---

### Comment Controller (`/api/blogs`)

| Method | Endpoint                         | Description             |
|--------|----------------------------------|-------------------------|
| POST   | `/{blogId}/comments`             | Add comment to blog     |
| GET    | `/{blogId}/comments`             | Get comments for a blog |
| DELETE | `/{blogId}/comments/{commentId}` | Delete comment          |

---

## Pagination Example

```
GET /api/blogs/getAllBlogs?page=0&size=10&sort=createdAt,desc
```

---

## Validation and Error Handling

* Request validation using `@Valid`
* Centralized exception handling
* Secure access using Spring Security

---

## How to Run Locally

### Clone the repository

```bash
git clone https://github.com/your-username/blog-platform-api.git
```

### Configure the database

Update `application.properties` or `application.yml`:

```properties
spring.application.name=blogging-platform-api

spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

### Run the application

```bash
mvn spring-boot:run
```

---

## Future Enhancements

* Role-based authorization
* Blog search functionality
* Swagger / OpenAPI documentation
* User profile image upload
* Redis caching

---

## Author

Prakhar Tripathi
B.Tech Computer Science
Java Backend Developer
Spring Boot | MySQL | REST APIs

---


