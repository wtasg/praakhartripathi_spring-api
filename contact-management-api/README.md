# Contact Management API

A RESTful API for managing contacts, built with Spring Boot, Spring Data JPA, and PostgreSQL.

## Technologies Used

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL**
* **Maven**

## Project Structure

The project follows a standard layered architecture:

* `controller`: Handles HTTP requests and responses.
* `service`: Contains business logic.
* `repository`: Handles data access using Spring Data JPA.
* `entity`: Defines the data model.

## Prerequisites

* Java 17 or higher installed.
* Maven installed.
* PostgreSQL installed and running.

## Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd contact-management-api
   ```

2. **Configure the Database:**
   Create a PostgreSQL database named `contact_db`.
   Update `src/main/resources/application.properties` if your credentials differ from the defaults:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/contact_db
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080`.

## API Endpoints

### 1. Create a Contact

* **URL:** `/api/contacts`
* **Method:** `POST`
* **Content-Type:** `application/json`

**Request Body:**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```

**Curl Command:**

```bash
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890"
  }'
```

### 2. Get All Contacts

* **URL:** `/api/contacts`
* **Method:** `GET`

**Curl Command:**

```bash
curl -X GET http://localhost:8080/api/contacts
```

### 3. Get Contact by ID

* **URL:** `/api/contacts/{id}`
* **Method:** `GET`

**Curl Command:**

```bash
curl -X GET http://localhost:8080/api/contacts/1
```

### 4. Update a Contact

* **URL:** `/api/contacts/{id}`
* **Method:** `PUT`
* **Content-Type:** `application/json`

**Request Body:**

```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "0987654321"
}
```

**Curl Command:**

```bash
curl -X PUT http://localhost:8080/api/contacts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phoneNumber": "0987654321"
  }'
```

### 5. Delete a Contact

* **URL:** `/api/contacts/{id}`
* **Method:** `DELETE`

**Curl Command:**

```bash
curl -X DELETE http://localhost:8080/api/contacts/1
```
