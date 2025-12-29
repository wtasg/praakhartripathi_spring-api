---

# Spring API Projects

This repository contains Spring Boot backend projects created for learning, practice, and real-world API development.

---

## Expense Tracker API

A RESTful Expense Tracker API built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.

* [expense-tracker](./expense-tracker)

### Features

* Create, Read, Update, Delete expenses
* PostgreSQL integration
* Layered architecture (Controller, Service, Repository)
* API tested using `curl`

---

## Blog Platform API

A fully-featured REST API for a blogging platform built using **Spring Boot**, **Spring Security (JWT)**, and **Spring Data JPA**.

* [blog-platform-api](./blog-platform-api)

### Description

The Blog Platform API provides backend functionality for a blogging system, including authentication, blog management, categories, likes, and comments.
It follows clean architecture principles and implements secure, production-ready APIs.

---

### Features

* User registration and login with JWT authentication
* Secure APIs using Spring Security
* Create, update, and delete blogs
* Pagination and sorting for blogs
* Like blogs and fetch like count
* Comment system for blogs
* Category-based blog organization
* Fetch blogs by user and category
* DTO-based request/response handling
* Centralized exception handling
* Layered architecture (Controller, Service, Repository)

---

### Key API Modules

* Auth APIs (Register, Login, Profile)
* Blog APIs (CRUD, Like, Pagination)
* Category APIs
* Comment APIs

---

## GitHub Builder API

A DevOps-style backend automation API built using **Spring Boot** that simulates a mini CI/CD pipeline.

* [github-builder-api](./github-builder-api)

### Description

GitHub Builder API automates the process of:

* Cloning GitHub repositories
* Detecting project type (Maven / Gradle)
* Building Java projects
* Generating JAR artifacts
* Copying build artifacts to both internal storage and user-defined locations

The system is API-only and fully testable using `curl` or Postman.

---

### Features

* Clone public GitHub repositories
* Detect Java project type (Maven / Gradle)
* Execute Maven and Gradle builds
* Locate generated JAR artifacts
* Copy artifacts to:

  * Internal server storage
  * User-defined destination path
* Centralized exception handling
* Clean layered architecture

---

### Example Workflow

```
Clone → Detect → Build → Locate Artifact → Copy
```

---

## Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* Maven

---

## Author

Prakhar Tripathi
B.Tech Computer Science
Java Backend Developer
Spring Boot | REST APIs | SQL | System Design

---


