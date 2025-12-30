

---

Task Tracker API

A production-ready RESTful Task Tracker backend built using Spring Boot and PostgreSQL.
This project demonstrates clean layered architecture, CRUD operations, filtering, pagination, and Dockerized database usage.

---

Project Overview

The Task Tracker API allows users to manage tasks with the following features:

* Create a task
* Get all tasks
* Get a task by ID
* Update task details
* Delete a task
* Filter tasks by status
* Pagination and sorting (production-level)

The backend is designed following industry best practices using Controller, Service, Repository, DTO, and Entity layers.

---

Tech Stack Used

Backend

* Java 17
* Spring Boot 3
* Spring Data JPA
* Hibernate

Database

* PostgreSQL 15
* Docker (for database containerization)

Build Tool

* Maven

Testing

* curl (CLI testing)

---

Database Setup (PostgreSQL with Docker)

Pull PostgreSQL image:

```
docker pull postgres:15
```

Run PostgreSQL container:

```
docker run -d \
  --name postgres-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=task_tracker \
  -p 5432:5432 \
  postgres:15
```

Verify database:

```
docker exec -it postgres-db psql -U postgres -d task_tracker
```

---

Application Configuration

application.yml (example)

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/task_tracker
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Hibernate automatically creates and updates tables based on entities.

---

Base URL

```
http://localhost:8080
```

All APIs are prefixed with:

```
/api/tasks
```

---

API Endpoints and curl Testing

1. Create Task

Endpoint:

```
POST /api/tasks/create
```

curl:

```
curl -X POST http://localhost:8080/api/tasks/create \
  -H "Content-Type: application/json" \
  -d '{
        "title": "Learn Spring Boot",
        "description": "Build task tracker API",
        "taskStatus": "IN_PROGRESS"
      }'
```

Response:

```
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": "Build task tracker API",
  "status": "IN_PROGRESS",
  "createdAt": "2025-09-01T10:30:00"
}
```

---

2. Get All Tasks

Endpoint:

```
GET /api/tasks/all
```

curl:

```
curl -X GET http://localhost:8080/api/tasks/all
```

Response:

```
[
  {
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "Build task tracker API",
    "status": "IN_PROGRESS"
  },
  {
    "id": 2,
    "title": "Write Resume",
    "description": "Add project details",
    "status": "PENDING"
  }
]
```

---

3. Get Task By ID

Endpoint:

```
GET /api/tasks/{id}
```

curl:

```
curl -X GET http://localhost:8080/api/tasks/1
```

Response:

```
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": "Build task tracker API",
  "status": "IN_PROGRESS"
}
```

---

4. Update Task

Endpoint:

```
PUT /api/tasks/{id}
```

curl:

```
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
        "title": "Learn Spring Boot Advanced",
        "description": "Add PostgreSQL + Security",
        "status": "IN_PROGRESS"
      }'
```

Response:

```
{
  "id": 1,
  "title": "Learn Spring Boot Advanced",
  "description": "Add PostgreSQL + Security",
  "status": "IN_PROGRESS",
  "updatedAt": "2025-09-01T12:00:00"
}
```

---

5. Delete Task

Endpoint:

```
DELETE /api/tasks/{id}
```

curl:

```
curl -X DELETE http://localhost:8080/api/tasks/1
```

Response:

```
task deleted successfully
```

---

6. Filter Tasks By Status

Endpoint:

```
GET /api/tasks/status/{status}
```

Valid status values:

```
PENDING
IN_PROGRESS
COMPLETED
```

curl:

```
curl -X GET http://localhost:8080/api/tasks/status/COMPLETED
```

Response:

```
[
  {
    "id": 3,
    "title": "Deploy API",
    "description": "Render deployment",
    "status": "COMPLETED"
  }
]
```

---

7. Pagination and Sorting

Endpoint:

```
GET /api/tasks
```

Query Parameters:

```
page        page number (default 0)
size        number of records per page (default 5)
sort        field name (default createdAt)
direction   asc or desc (default desc)
```

curl:

```
curl -X GET "http://localhost:8080/api/tasks?page=0&size=5&sort=createdAt&direction=desc"
```

Response:

```
{
  "content": [
    {
      "id": 5,
      "title": "Deploy API",
      "description": "Render deployment",
      "status": "COMPLETED",
      "createdAt": "2025-09-01T12:00:00"
    }
  ],
  "totalPages": 3,
  "totalElements": 13
}
```

---

Key Highlights

* Clean RESTful API design
* Layered architecture (Controller, Service, Repository)
* PostgreSQL database with Docker
* Enum-based task status handling
* Pagination and sorting for large datasets
* Production-ready code structure

---

