TMDB CLI Tool

A Spring Boot application that provides a command-line interface (CLI) and REST API for searching and retrieving movie and TV show information. It currently integrates with the OMDB API to fetch data.

Features

- Search for Movies and TV Shows by title.
- Get detailed information for a specific Movie or TV Show by IMDb ID or Title.
- View lists of "Popular" and "Top Rated" Movies and TV Shows (simulated via keyword search).
- RESTful API endpoints for easy integration.

Prerequisites

- Java 17 or higher
- Maven
- An OMDB API Key (Get one for free at http://www.omdbapi.com/apikey.aspx)

Configuration

1. Open the src/main/resources/application.properties file.
2. Add your OMDB API key:

omdb.api.key=YOUR_API_KEY
omdb.api.url=https://www.omdbapi.com

Build and Run

1. Clone the repository:
   git clone https://github.com/your-username/TMDB-CLI-Tool.git

2. Navigate to the project directory:
   cd TMDB-CLI-Tool

3. Build the project using Maven:
   mvn clean install

4. Run the application:
   mvn spring-boot:run

The application will start on http://localhost:8080.

API Endpoints

Movies

- Search Movies:
  GET /api/movies/search?query={title}
  Example: curl "http://localhost:8080/api/movies/search?query=Batman"

- Get Movie Detail by IMDb ID:
  GET /api/movies/{imdbId}
  Example: curl "http://localhost:8080/api/movies/tt0372784"

- Get Movie Detail by Title:
  GET /api/movies/detail?title={title}
  Example: curl "http://localhost:8080/api/movies/detail?title=Inception"

- Get Popular Movies (Simulated):
  GET /api/movies/popular

- Get Top Rated Movies (Simulated):
  GET /api/movies/top-rated

TV Shows (Series)

- Search TV Shows:
  GET /api/series/search?query={title}
  Example: curl "http://localhost:8080/api/series/search?query=Friends"

- Get TV Show Detail by Title:
  GET /api/series/detail?title={title}
  Example: curl "http://localhost:8080/api/series/detail?title=Breaking%20Bad"

- Get Popular TV Shows (Simulated):
  GET /api/series/popular

- Get Top Rated TV Shows (Simulated):
  GET /api/series/top-rated

Project Structure

- src/main/java/com/TMDB_CLI_Tool/controller: Contains the REST controller (MovieController).
- src/main/java/com/TMDB_CLI_Tool/service: Contains the business logic and API integration (MovieService).
- src/main/java/com/TMDB_CLI_Tool/dto: Contains Data Transfer Objects (MovieDto, OmdbSearchResponse).

License

This project is licensed under the MIT License.
