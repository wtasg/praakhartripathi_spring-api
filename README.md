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

--

---

## User Authentication Service

### Project

A backend User Authentication Service built using Spring Boot that provides secure user registration, login, and password management using JWT-based authentication.

* [ user-authentication-service ](./user-authentication-service)
---

### Details

This project implements industry-standard authentication and authorization mechanisms. It uses Spring Security with JWT for stateless authentication, BCrypt for password encryption, and supports role-based access control.

---

### Features

* User registration
* User login with JWT token generation
* Forgot password functionality
* Update password with current password verification
* JWT-based authentication
* Role-based access control (USER, ADMIN)
* Secure password hashing using BCrypt

---


### URL Shortener API

A production-grade URL Shortener REST API built using Spring Boot, Spring Data JPA, and PostgreSQL.
* [url-shortner](./url-shortner)

## Features
* Create short URLs from long URLs
* Redirect short URLs to original URLs
* Expiry-based short URLs
* Click count and last access analytics
* View URL details
* Delete short URLs
* PostgreSQL database integration
* Layered architecture (Controller, Service, Repository)

---

## TMDB-CLI-Tool
A Spring Boot–based movie and TV show search tool that exposes REST APIs and a CLI, integrating with the OMDb API to fetch details by title or IMDb ID and simulate popular and top-rated content using smart search strategies.
* [TMDB-CLI-Tool](./TMDB-CLI-Tool)

### Features
* Search movies and TV shows by title
* Fetch detailed information using IMDb ID or name
* Simulated popular movies and TV shows
* Simulated top-rated movies and TV shows
* RESTful APIs for easy integration
* Clean layered architecture (Controller, Service, DTO)

---

## Task Tracker API

A RESTful Task Tracker API built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.

* [task-tracker-api](./task-tracker-api)

### Features

* Create, Read, Update, Delete tasks
* Get task by ID
* Filter tasks by status (PENDING, IN_PROGRESS, COMPLETED)
* Pagination and sorting support
* PostgreSQL integration using Docker
* Layered architecture (Controller, Service, Repository, DTO, Entity)
* API tested using `curl`

---

## Blog Platform API

A fully-featured REST API for a blogging platform built using **Spring Boot**, **Spring Security (JWT)**, and **Spring Data JPA**.

* [blogging-platform-api](./blogging-platform-api)

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

---

## OTP Login API (Learning Project)

A simple OTP-based login API built using **Spring Boot** to understand the fundamentals of authentication and request handling.

* [otp-login](./otp-login)

### Description

This project demonstrates a basic phone number and OTP login flow without using any external SMS gateway or database.
OTP is generated on the backend, stored temporarily in memory, and printed in the server console to simulate SMS delivery.
All APIs are designed to be tested using `curl`.

---

### Features

* Generate OTP for a phone number
* OTP expiry handling
* In-memory OTP storage using concurrent data structures
* OTP verification and login simulation
* Stateless REST APIs
* No database required
* Fully testable using `curl`

---

### API Endpoints

#### Send OTP

```
POST /api/auth/send-otp
```

#### Verify OTP

```
POST /api/auth/verify-otp
```

---

### Learning Focus

* Understanding OTP authentication flow
* Temporary data storage and expiry logic
* REST API design with Spring Boot
* Debugging API issues using curl
* Building authentication systems without external dependencies

---

### Notes

This project is intended **only for learning purposes**.
In real-world applications, OTPs should be delivered via SMS/email providers and stored in in-memory stores like Redis, while user data should be persisted in a database.

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


