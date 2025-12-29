
---

# Spring Boot OTP Login (Learning Project)

## Project Name

```
springboot-otp-login-learning
```

---

## Description

This is a **learning-focused OTP login API** built using **Spring Boot**.

* Login using **phone number + OTP**
* OTP is generated on backend
* OTP is stored **in memory**
* OTP is printed in server console (simulating SMS)
* Entire API can be tested using **curl**
* No database required

This project is meant to understand the **core OTP authentication flow**, not production SMS delivery.

---

## Features

* Generate OTP
* OTP expiry handling
* OTP verification
* Stateless API
* curl-based testing
* No frontend required

---

## Tech Stack

* Java 17+
* Spring Boot 3.x
* Spring Web
* In-memory storage (`ConcurrentHashMap`)

---

## API Endpoints

### 1. Send OTP

**URL**

```
POST /api/auth/send-otp
```

**Request Body**

```json
{
  "phone": "9999999999"
}
```

**curl Command**

```bash
curl -X POST http://localhost:8080/api/auth/send-otp \
-H "Content-Type: application/json" \
-d '{"phone":"9999999999"}'
```

**Response**

```
otp sent successfully
```

Check server console for OTP:

```
OTP for 9999999999 is: 178396
```

---

### 2. Verify OTP

**URL**

```
POST /api/auth/verify-otp
```

**Request Body**

```json
{
  "phone": "9999999999",
  "otp": "178396"
}
```

**Correct curl Command**

```bash
curl -X POST http://localhost:8080/api/auth/verify-otp \
-H "Content-Type: application/json" \
-d '{"phone":"9999999999","otp":"178396"}'
```

**Success Response**

```
Login successful
```

**Failure Response**

```
Invalid OTP
```

---

## Common Issue: 404 Not Found on `/verify-otp`

### Problem

You may see this error:

```json
{
  "status": 404,
  "error": "Not Found",
  "path": "/api/auth/verify-otp"
}
```

### Reason

This happens due to **incorrect curl command formatting**, usually caused by:

* Copy-paste mistakes
* Duplicate `curl -X POST`
* Trailing backslashes with extra text

Example of incorrect command:

```bash
curl -X POST http://localhost:8080/api/auth/verify-otp \   curl -X POST http://localhost:8080/api/auth/verify-otp
```

Spring Boot receives an invalid request path, so it returns 404.

---

### Solution

Use **only one curl command**, exactly like this:

```bash
curl -X POST http://localhost:8080/api/auth/verify-otp \
-H "Content-Type: application/json" \
-d '{"phone":"9999999999","otp":"178396"}'
```

---

## Project Structure

```
springboot-otp-login-learning
├── controller
│   └── AuthController.java
├── service
│   └── OtpService.java
├── model
│   └── OtpData.java
└── SpringbootOtpLoginLearningApplication.java
```

---

## Why No Database?

* OTPs are temporary
* OTPs expire quickly
* OTPs should not be stored permanently

For learning and single-instance applications:

* In-memory storage is sufficient

In real production systems:

* OTPs are stored in Redis
* Databases are used only for user data

---

## Learning Outcomes

* OTP generation logic
* Expiry handling
* Authentication flow
* REST API design
* curl-based API testing
* Common debugging mistakes

---

## Future Improvements

* JWT token generation after OTP verification
* User persistence using MySQL or PostgreSQL
* Redis-based OTP storage
* Rate limiting
* SMS gateway integration

---

## License

This project is for learning and educational purposes.

---


