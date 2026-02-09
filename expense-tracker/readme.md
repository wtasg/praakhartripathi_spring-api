---

# 📘 Expense Tracker Spring Boot API

A **Spring Boot REST API** for managing daily expenses with full **CRUD operations**, connected to **PostgreSQL** and tested using **curl**.

---

## 🚀 Features

* Create expense
* Get all expenses
* Get expense by ID
* Update expense
* Delete expense
* PostgreSQL integration
* Clean layered architecture

---

## 🧱 Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* curl (API testing)

---

## 📂 Project Structure

```
expense-tracker
├── src/main/java/com/expense_tracker
│   ├── controller
│   │   └── ExpenseController.java
│   ├── dto
│   │   └── ExpenseRequestDTO.java
│   ├── entity
│   │   └── Expense.java
│   ├── repository
│   │   └── ExpenseRepository.java
│   ├── service
│   │   ├── ExpenseService.java
│   │   └── ExpenseServiceImpl.java
│   └── ExpenseTrackerApplication.java
│
├── src/main/resources
│   └── application.properties
│
├── pom.xml
└── README.md
```

---

## ⚙️ Application Configuration

```properties
spring.application.name=expense-tracker

spring.datasource.url=jdbc:postgresql://localhost:5432/expense_db
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

## ▶️ Run the Application

```bash
./mvnw spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

## 🧪 API Testing Using curl

### ➕ Create Expense

```bash
curl -X POST http://localhost:8080/api/expenses \
-H "Content-Type: application/json" \
-d '{
  "userId": 1,
  "title": "Lunch",
  "amount": 250,
  "category": "Food",
  "expenseDate": "2025-01-25"
}'
```

✅ Response:

```json
{
  "userId": 1,
  "title": "Lunch",
  "amount": 250.0,
  "category": "Food",
  "expenseDate": "2025-01-25",
  "createdAt": "2025-12-25T19:xx:xx"
}
```

---

### 📄 Get All Expenses

```bash
curl -X GET http://localhost:8080/api/expenses
```

```json
[
  {
    "userId": 1,
    "title": "Lunch",
    "amount": 250.0,
    "category": "Food",
    "expenseDate": "2025-01-25",
    "createdAt": "2025-12-25T19:xx:xx"
  }
]
```

---

### 🔍 Get Expense by ID

```bash
curl -X GET http://localhost:8080/api/expenses/1
```

---

### ✏️ Update Expense

```bash
curl -X PUT http://localhost:8080/api/expenses/1 \
-H "Content-Type: application/json" \
-d '{
  "userId": 1,
  "title": "Dinner",
  "amount": 400,
  "category": "Food",
  "expenseDate": "2025-01-26"
}'
```

---

### ❌ Delete Expense

```bash
curl -X DELETE http://localhost:8080/api/expenses/1
```

```text
Expense deleted successfully
```

---

## 🧠 Architecture Overview

```
Controller → Service → Repository → Database
```

* **Controller**: Handles HTTP requests
* **Service**: Business logic
* **Repository**: Database operations
* **Entity**: Database mapping
* **DTO**: Safe data transfer

---

## 🎯 Interview Ready Summary

> Built a Spring Boot REST API for expense tracking using PostgreSQL, implemented full CRUD operations, followed layered
> architecture, and tested APIs using curl.

---

## 🚀 Future Enhancements

* Global exception handling
* Input validation
* JWT authentication
* User-based filtering
* Pagination & sorting

---

## 👤 Author

**Prakhar Tripathi**
GitHub: [https://github.com/praakhartripathi](https://github.com/praakhartripathi)

---
