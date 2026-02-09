# URL Shortener API

A production-grade URL shortener built with Spring Boot and PostgreSQL. It supports creating short URLs, redirection,
analytics, expiration, and deletion.

## Features

* **Shorten URLs**: Convert long URLs into short, manageable codes.
* **Redirection**: Redirect users from short codes to original URLs.
* **Expiration**: Set an expiration date/time for short URLs.
* **Analytics**: Track click counts and last access time.
* **Management**: View details and delete short URLs.

## Tech Stack

* **Java 17+**
* **Spring Boot 3.x**
* **PostgreSQL**
* **Hibernate / JPA**

## Getting Started

### Prerequisites

* Java 17 or higher
* PostgreSQL database running on localhost:5432 (Database name: `url_sortner`)

### Configuration

The application runs on port **8081**.
Database credentials are configured in `src/main/resources/application.properties`.

### Running the Application

```bash
./mvnw spring-boot:run
```

## API Endpoints

### 1. Create Short URL

**POST** `/api/v2/urls/shorturl`

Creates a new short URL. Optional expiry date.

**Request Body:**

```json
{
  "originalUrl": "https://google.com",
  "expiresAt": "2026-12-31T23:59:59"  // Optional
}
```

**Response:**

```json
{
  "shortUrl": "http://localhost:8081/u/abc1234",
  "code": "abc1234"
}
```

### 2. Create Expiry-Based Short URL (Alternative)

**POST** `/api/v1/urls/expire`

Same as above, specifically for creating expiring URLs.

**Request Body:**

```json
{
  "originalUrl": "https://example.com",
  "expiresAt": "2026-01-10T23:59:59"
}
```

### 3. Redirect to Original URL

**GET** `/u/{shortCode}`

Redirects the user to the original URL. Returns `302 Found`.
Returns `404 Not Found` if the code doesn't exist.
Returns `500 Internal Server Error` (with message) if expired.

### 4. Get URL Details

**GET** `/api/v1/urls/{shortCode}`

Returns basic details about the short URL.

**Response:**

```json
{
  "originalUrl": "https://google.com",
  "code": "abc1234",
  "createdAt": "2026-01-02T10:00:00"
}
```

### 5. Get Full URL Details (Admin)

**GET** `/api/v1/urls/{shortCode}/details`

Returns comprehensive details including expiry and click count.

**Response:**

```json
{
  "originalUrl": "https://google.com",
  "createdAt": "2026-01-02T10:00:00",
  "expiresAt": "2026-12-31T23:59:59",
  "clickCount": 42
}
```

### 6. Get Analytics

**GET** `/api/url/{shortCode}/analytics`

Returns analytics data for a specific short URL.

**Response:**

```json
{
  "clickCount": 42,
  "lastAccessedAt": "2026-01-02T12:30:00"
}
```

### 7. Delete Short URL

**DELETE** `/api/v1/urls/{shortCode}`

Deletes the short URL. Returns `204 No Content` on success.
