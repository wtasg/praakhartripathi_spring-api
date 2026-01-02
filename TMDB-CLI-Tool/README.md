---

# 🎬 **TMDB CLI Tool**

A **Spring Boot** application that provides both a **Command-Line Interface (CLI)** and **REST APIs** for searching and retrieving **Movies and TV Shows**.
Currently, it integrates with the **OMDb API** to fetch movie and series data.

---

## 🚀 **Features**

* 🔍 **Search Movies and TV Shows** by title
* 🎞️ **Get detailed information** using:

  * IMDb ID
  * Movie / Series title
* ⭐ **Popular Movies & TV Shows** (simulated using curated keyword searches)
* 🏆 **Top Rated Movies & TV Shows** (simulated using IMDb data)
* 🌐 **RESTful APIs** for easy integration with frontend or CLI tools
* 🧩 Clean layered architecture (**Controller → Service → DTO**)

---

## 🛠️ **Tech Stack**

* ☕ **Java 17+**
* 🌱 **Spring Boot**
* 🔗 **Spring WebClient**
* 📦 **Maven**
* 🎥 **OMDb API**

---

## ✅ **Prerequisites**

Before running the project, make sure you have:

* Java **17 or higher**
* Maven installed
* An **OMDb API Key**
  👉 Get one for free: [http://www.omdbapi.com/apikey.aspx](http://www.omdbapi.com/apikey.aspx)

---

## ⚙️ **Configuration**

1. Open the file:

```bash
src/main/resources/application.properties
```

2. Add your OMDb credentials:

```properties
omdb.api.key=YOUR_API_KEY
omdb.api.url=https://www.omdbapi.com
```

---

## 🏗️ **Build & Run**

### 1️⃣ Clone the repository

```bash
git clone https://github.com/praakhartripathi/spring-api.git
```

### 2️⃣ Navigate to the project directory

```bash
cd spring-api/TMDB-CLI-Tool
```

### 3️⃣ Build the project

```bash
mvn clean install
```

### 4️⃣ Run the application

```bash
mvn spring-boot:run
```

🚀 Application will start at:
**[http://localhost:8080](http://localhost:8080)**

---

## 🌐 **API Endpoints**

### 🎬 Movies

#### 🔍 Search Movies

```http
GET /api/movies/search?query={title}
```

Example:

```bash
curl "http://localhost:8080/api/movies/search?query=Batman"
```

---

#### 🎞️ Get Movie Detail by IMDb ID

```http
GET /api/movies/{imdbId}
```

Example:

```bash
curl "http://localhost:8080/api/movies/tt0372784"
```

---

#### 📖 Get Movie Detail by Title

```http
GET /api/movies/detail?title={title}
```

Example:

```bash
curl "http://localhost:8080/api/movies/detail?title=Inception"
```

---

#### ⭐ Popular Movies (Simulated)

```http
GET /api/movies/popular
```

---

#### 🏆 Top Rated Movies (Simulated)

```http
GET /api/movies/top-rated
```

---

### 📺 TV Shows (Series)

#### 🔍 Search TV Shows

```http
GET /api/series/search?query={title}
```

Example:

```bash
curl "http://localhost:8080/api/series/search?query=Friends"
```

---

#### 📖 Get TV Show Detail by Title

```http
GET /api/series/detail?title={title}
```

Example:

```bash
curl "http://localhost:8080/api/series/detail?title=Breaking%20Bad"
```

---

#### ⭐ Popular TV Shows (Simulated)

```http
GET /api/series/popular
```

---

#### 🏆 Top Rated TV Shows (Simulated)

```http
GET /api/series/top-rated
```

---

## 🗂️ **Project Structure**

```
src/main/java/com/TMDB_CLI_Tool
│
├── controller   → REST Controllers
├── service      → Business logic & OMDb API integration
├── dto          → Data Transfer Objects (DTOs)
└── TMDBCliToolApplication.java
```

---

## 💡 **Design Notes**

* OMDb does **not** provide real popularity or trending endpoints
* “Popular” and “Top Rated” APIs are **simulated** using:

  * IMDb Top movie IDs
  * Franchise-based keyword searches
* The architecture allows **easy switching** to TMDB or other providers in the future

---

## 📜 **License**

This project is licensed under the **MIT License**.

---

## 🔗 **Repository**

👉 [https://github.com/praakhartripathi/spring-api/tree/main/TMDB-CLI-Tool](https://github.com/praakhartripathi/spring-api/tree/main/TMDB-CLI-Tool)

---

