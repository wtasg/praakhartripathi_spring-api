# Feedback and Review System

A scalable Feedback & Review System built using Spring Boot, JPA, and PostgreSQL. This system allows users to submit
reviews for products, enforcing a unique review per user per product rule, and provides APIs for retrieving reviews and
calculating average ratings.

## Features

* **Create Review**: Users can submit a review (rating and comment) for a product.
* **One Review Per User**: Ensures a user can only review a specific product once.
* **Get Reviews**: Retrieve a paginated list of reviews for a product.
* **Average Rating**: Calculate and retrieve the average rating for a product.
* **Delete Review**: Users can delete their own reviews.
* **Check Review Status**: Check if a user has already reviewed a product.

## Technologies Used

* **Java 17**
* **Spring Boot 3.2.1**
* **Spring Data JPA**
* **PostgreSQL**
* **Maven**

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd feedback-and-review-sytstem
   ```

2. **Configure Database**:
    * Ensure you have PostgreSQL installed and running.
    * Create a database named `feedback_db`.
    * Update `src/main/resources/application.properties` with your database credentials if they differ from the
      defaults:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/feedback_db
      spring.datasource.username=postgres
      spring.datasource.password=postgres
      ```

3. **Build and Run**:
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints

### 1. Create a Review

**POST** `/api/reviews`

Request Body:

```json
{
  "userId": 1,
  "productId": 101,
  "rating": 5,
  "comment": "Excellent product!"
}
```

Curl Command:

```bash
curl -X POST http://localhost:8080/api/reviews \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "productId": 101,
    "rating": 5,
    "comment": "Excellent product!"
}'
```

### 2. Get Reviews for a Product (Paginated)

**GET** `/api/reviews/product/{productId}`

Query Parameters:

* `page`: Page number (default 0)
* `size`: Page size (default 10)
* `sort`: Sorting criteria (default `id,desc`)

Curl Command:

```bash
curl -X GET "http://localhost:8080/api/reviews/product/101?page=0&size=5&sort=rating,desc"
```

### 3. Get Average Rating for a Product

**GET** `/api/reviews/product/{productId}/average-rating`

Curl Command:

```bash
curl -X GET http://localhost:8080/api/reviews/product/101/average-rating
```

### 4. Check if User Reviewed a Product

**GET** `/api/reviews/check`

Query Parameters:

* `userId`: ID of the user
* `productId`: ID of the product

Curl Command:

```bash
curl -X GET "http://localhost:8080/api/reviews/check?userId=1&productId=101"
```

### 5. Delete a Review

**DELETE** `/api/reviews`

Query Parameters:

* `userId`: ID of the user
* `productId`: ID of the product

Curl Command:

```bash
curl -X DELETE "http://localhost:8080/api/reviews?userId=1&productId=101"
```
