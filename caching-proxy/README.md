# Caching Proxy Service

A Spring Boot-based caching proxy application that intercepts client requests, caches external API responses using
Spring Cache with Caffeine, and reduces latency and redundant API calls.

## Features

* **Proxy Endpoint**: Forwards requests to an external API.
* **Caching**: Caches responses using Caffeine to improve performance and reduce load on external services.
* **Configurable Cache**: Default configuration expires entries after 10 minutes and holds up to 1000 items.

## Tech Stack

* Java 17
* Spring Boot 3.3.0
* Spring Web
* Spring Cache
* Caffeine Cache

## Prerequisites

* JDK 17 or later
* Maven 3.6+

## Getting Started

### Build the Application

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on port `8080` by default.

## Usage

To use the proxy, send a GET request to the `/proxy` endpoint with the `url` query parameter pointing to the external
API you want to fetch.

### Example

Using `curl`:

```bash
curl "http://localhost:8080/proxy?url=https://jsonplaceholder.typicode.com/todos/1"
```

**Response:**

```json
{
  "userId": 1,
  "id": 1,
  "title": "delectus aut autem",
  "completed": false
}
```

### Verifying Cache

1. **First Request**: The application logs "Calling external API..." and fetches data from the source.
2. **Subsequent Requests**: The application serves the response from the cache (no log message) until the cache expires.

## Configuration

The cache configuration can be found in `src/main/java/com/caching_proxy/config/CacheConfig.java`.

* **Cache Name**: `proxyCache`
* **TTL (Time To Live)**: 10 minutes
* **Max Size**: 1000 entries
