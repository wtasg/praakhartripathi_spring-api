# Image Processing Service

A scalable Image Processing Microservice built with Spring Boot. This service supports image uploading, resizing,
compression, format conversion (including WebP, TIFF, BMP, ICO), and thumbnail generation.

## Features

* **Upload & Process**: Upload images with optional resizing, compression, and format conversion.
* **Format Conversion**: Convert existing images to JPG, PNG, WebP, TIFF, BMP, and ICO.
* **Thumbnail Generation**: Generate thumbnails on-the-fly.
* **Upload from URL**: Fetch and process images directly from a URL.
* **Pagination**: List all uploaded images with pagination support.
* **Metadata**: Retrieve image metadata.
* **Deletion**: Delete images and their metadata.

## Tech Stack

* **Java 17+**
* **Spring Boot 3.2.x**
* **Spring Data JPA** (PostgreSQL)
* **Thumbnailator** (Image Processing)
* **TwelveMonkeys ImageIO** (Extended Format Support: WebP, TIFF, etc.)
* **Image4J** (ICO Support)
* **Lombok**

## Getting Started

### Prerequisites

* Java 17 or higher
* PostgreSQL (running on localhost:5432)

### Configuration

Update `src/main/resources/application.properties` if your database credentials differ:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/image_processing_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Build & Run

1. **Build the project**:
   ```sh
   ./mvnw clean package -DskipTests
   ```

2. **Run the application**:
   ```sh
   java -jar target/image-processing-service-0.0.1-SNAPSHOT.jar
   ```

The service will start on `http://localhost:8080`.

## API Endpoints

### 1. Upload Image

**POST** `/api/v1/images/upload`

Uploads an image file.

* **Body (Multipart)**:
    * `file`: The image file (Required)
    * `width`: Target width (Optional)
    * `height`: Target height (Optional)
    * `quality`: Compression quality 0.0 - 1.0 (Optional)
    * `format`: Output format e.g., jpg, png, webp (Optional)

**Curl Example**:

```sh
curl -X POST http://localhost:8080/api/v1/images/upload \
  -F "file=@/path/to/image.jpg" \
  -F "width=300" \
  -F "height=300" \
  -F "format=webp"
```

### 2. Upload from URL

**POST** `/api/v1/images/upload-from-url`

Fetches an image from a URL and processes it.

* **Body (JSON)**:
  ```json
  {
    "imageUrl": "https://example.com/image.jpg",
    "width": 400,
    "height": 400
  }
  ```

**Curl Example**:

```sh
curl -X POST http://localhost:8080/api/v1/images/upload-from-url \
  -H "Content-Type: application/json" \
  -d '{"imageUrl": "https://example.com/sample.jpg", "width": 400}'
```

### 3. Convert Image

**POST** `/api/v1/images/{id}/convert`

Converts an existing image to another format.

* **Params**:
    * `format`: Target format (jpg, png, webp, tiff, bmp, ico)

**Curl Example**:

```sh
curl -X POST "http://localhost:8080/api/v1/images/1/convert?format=png"
```

### 4. Generate Thumbnail

**POST** `/api/v1/images/{id}/thumbnail`

Generates a thumbnail for an existing image.

* **Params**:
    * `width`: Thumbnail width (Default: 150)
    * `height`: Thumbnail height (Default: 150)

**Curl Example**:

```sh
curl -X POST "http://localhost:8080/api/v1/images/1/thumbnail?width=100&height=100"
```

### 5. List All Images

**GET** `/api/v1/images`

Lists uploaded images with pagination.

* **Params**:
    * `page`: Page number (Default: 0)
    * `size`: Page size (Default: 10)

**Curl Example**:

```sh
curl -v -X GET "http://localhost:8080/api/v1/images?page=0&size=10"
```

### 6. Get Image Metadata

**GET** `/api/v1/images/{id}`

Retrieves metadata for a specific image.

**Curl Example**:

```sh
curl -v -X GET http://localhost:8080/api/v1/images/1
```

### 7. Delete Image

**DELETE** `/api/v1/images/{id}`

Deletes an image and its metadata.

**Curl Example**:

```sh
curl -X DELETE http://localhost:8080/api/v1/images/1
```
