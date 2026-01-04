# Weather Info API

A Spring Boot application that acts as a wrapper around the OpenWeatherMap API. It provides a simplified RESTful interface to retrieve current weather information for a specific city.

## Features

*   **City-based Weather Lookup:** Retrieve temperature, humidity, and weather description for any city.
*   **Simplified Response:** Returns a clean JSON object with essential weather details.
*   **Secure Configuration:** API keys are managed via application properties.

## Tech Stack

*   **Java 17**
*   **Spring Boot 3.5.9**
*   **Maven**
*   **OpenWeatherMap API**

## Prerequisites

*   Java 17 or higher installed.
*   Maven installed (or use the included `mvnw` wrapper).
*   An API Key from [OpenWeatherMap](https://openweathermap.org/api).

## Configuration

1.  Open `src/main/resources/application.properties`.
2.  Update the `weather.api.key` property with your OpenWeatherMap API key.

```properties
weather.api.key=YOUR_API_KEY_HERE
weather.api.url=https://api.openweathermap.org/data/2.5/weather
```

## Running the Application

You can run the application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Get Weather by City

**Request:**

```http
GET /api/weather/{city}
```

**Example:**

```bash
curl http://localhost:8080/api/weather/London
```

**Response:**

```json
{
  "city": "London",
  "temperature": 15.5,
  "humidity": 72,
  "description": "scattered clouds"
}
```

## Project Structure

*   `controller`: Handles HTTP requests (`WeatherController`).
*   `service`: Contains business logic and external API calls (`WeatherService`).
*   `dto`: Data Transfer Objects for API responses (`WeatherResponse`).
