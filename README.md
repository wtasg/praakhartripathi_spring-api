# Spring API Projects 

This repository contains Spring Boot backend projects created for learning, practice, and real-world API development.

---

## Expense Tracker API

A RESTful Expense Tracker API built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.

- [expense-tracker](./expense-tracker)

### Features
- Create, Read, Update, Delete expenses
- PostgreSQL integration
- Layered architecture (Controller, Service, Repository)
- API tested using `curl`

---

---
##  GitHub Builder API

A DevOps-style backend automation API built using **Spring Boot** that simulates a **mini CI/CD pipeline**.


- [github-builder-api](./github-builder-api)

### Description

GitHub Builder API automates the process of:

* Cloning GitHub repositories
* Detecting project type (Maven / Gradle)
* Building Java projects
* Generating JAR artifacts
* Copying build artifacts to both internal storage and user-defined locations

The system is **API-only** and fully testable using `curl` or Postman.

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


## 🧠 Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

---

👤 **Author**  
Prakhar Tripathi  
