---

# GitHub Builder API

GitHub Builder API is a Spring Boot–based backend service that automates **GitHub repository cloning, project type detection, Java build execution, and artifact distribution**.
It behaves like a **mini CI/CD pipeline** and is fully operable via REST APIs using `curl` or Postman (no UI).

---

## Features

* Clone public GitHub repositories
* Detect project type (Maven / Gradle)
* Build Java projects automatically
* Generate JAR artifacts
* Copy build artifacts to:

  * Internal server storage
  * User-defined destination path
* Clean layered architecture
* Centralized exception handling
* API-only (no frontend)

---

##  High-Level Workflow

```
Clone Repository
        ↓
Detect Project Type
        ↓
Build Project
        ↓
Locate Artifact (JAR)
        ↓
Copy Artifact (Internal + User Path)
```

---

## Project Structure

```
github-builder-api
│
├── src/main/java/com/githubbuilder/github_builder_api
│   │
│   ├── controller
│   │   ├── BuildController.java
│   │   └── GitCloneController.java
│   │
│   ├── dto
│   │   ├── ArtifactDistributionResponse.java
│   │   ├── BuildRequest.java
│   │   ├── BuildResponse.java
│   │   ├── CloneRequest.java
│   │   ├── CloneResponse.java
│   │   └── DetectRequest.java
│   │
│   ├── ExceptionHandler
│   │   └── GlobalExceptionHandler.java
│   │
│   ├── service
│   │   ├── ArtifactCopyService.java
│   │   ├── BuildService.java
│   │   ├── GitCloneService.java
│   │   └── ProjectDetectionService.java
│   │
│   ├── ProjectType.java
│   └── GithubBuilderApiApplication.java
│
├── storage
│   └── builds
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

##  Tech Stack

* Java 21
* Spring Boot
* Maven
* JGit
* Java NIO (File handling)
* REST APIs
* curl / Postman for testing

---

##  Prerequisites

Ensure the following are installed:

```bash
java -version
mvn -v
```

Environment variable:

```bash
JAVA_HOME
```

---

##  API Endpoints

###  Clone GitHub Repository

**POST** `/api/github/clone`

```json
{
  "githubUrl": "https://github.com/user/repo",
  "targetPath": "/home/user/clones/repo"
}
```

Response:

```text
Repository cloned successfully
```

---

### Detect Project Type

**POST** `/api/github/detect`

```json
{
  "repoPath": "/home/user/clones/repo"
}
```

Response:

```json
{
  "repoPath": "/home/user/clones/repo",
  "projectType": "MAVEN"
}
```

---

###  Build Project and Copy Artifacts

This endpoint builds the project and copies the generated JAR to both internal and user-defined locations.

**POST** `/api/github/build`

```json
{
  "repoPath": "/home/user/clones/repo",
  "targetPath": "/home/user/output"
}
```

Response:

```json
{
  "status": "SUCCESS",
  "internalPath": "storage/builds/repo/app.jar",
  "userPath": "/home/user/output/app.jar"
}
```

---

##  Example End-to-End Usage (curl)

### Clone Repository

```bash
curl -X POST http://localhost:8080/api/github/clone \
-H "Content-Type: application/json" \
-d '{
  "githubUrl": "https://github.com/wtasg/meetonline",
  "targetPath": "/home/nerd/clones/meetonline"
}'
```

---

### Detect Project Type

```bash
curl -X POST http://localhost:8080/api/github/detect \
-H "Content-Type: application/json" \
-d '{
  "repoPath": "/home/nerd/clones/meetonline"
}'
```

---

### Build and Copy Artifact

```bash
curl -X POST http://localhost:8080/api/github/build \
-H "Content-Type: application/json" \
-d '{
  "repoPath": "/home/nerd/clones/meetonline",
  "targetPath": "/home/nerd/output"
}'
```

---

##  Artifact Storage

### Internal Storage

```
storage/builds/{project-name}/
```

Example:

```
storage/builds/meetonline/hrms-0.0.1-SNAPSHOT.jar
```

### User-Defined Location

```
/home/nerd/output/hrms-0.0.1-SNAPSHOT.jar
```

---

##  Error Handling

Centralized exception handling using `@RestControllerAdvice`.

* Invalid project type → `400 BAD REQUEST`
* Directory already exists → `400 BAD REQUEST`
* Build failure → `500 INTERNAL SERVER ERROR`
* File system errors handled safely

---

## Difficulty & Category

* Difficulty: Medium to Hard
* Category: DevOps Automation / Build Tooling API

---

##  Future Enhancements

* Asynchronous build jobs
* Build log streaming
* Artifact download API
* Docker-based isolated builds
* Multi-module project support
* Authentication and authorization

---

##  License

This project is open for learning and educational purposes.

---

#  Author

**Prakhar Tripathi**
Spring Boot | Java | Backend | DevOps Automation

---

