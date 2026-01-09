# WebSocket Broadcast Server

A simple Spring Boot application that demonstrates a WebSocket broadcast server. It allows clients to connect via WebSocket and receive messages broadcasted via a REST API endpoint.

## Features

- **WebSocket Endpoint**: `/broadcast` for clients to listen for messages.
- **REST API**: `POST /api/broadcast` to send messages to all connected WebSocket clients.
- **Simple Web Client**: An `index.html` page to test the WebSocket connection and view received messages.

## Prerequisites

- Java 17 or higher
- Maven

## Getting Started

### 1. Build and Run the Application

```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:8080`.

### 2. Connect a WebSocket Client

Open your web browser and navigate to:

```
http://localhost:8080/index.html
```

You should see the status change to **Connected**.

### 3. Broadcast a Message

Use `curl` or any HTTP client (like Postman) to send a POST request to the broadcast API.

```bash
curl -X POST http://localhost:8080/api/broadcast \
     -H "Content-Type: application/json" \
     -d '{
           "sender": "SYSTEM",
           "content": "Hello, WebSocket clients!"
         }'
```

### 4. Verify

Check the browser window where `index.html` is open. You should see the message you sent via `curl` appear in the list.

## Project Structure

- **Controller**: `BroadcastController` handles the REST API requests.
- **Service**: `BroadcastService` manages WebSocket sessions and broadcasts messages.
- **Handler**: `BroadcastWebSocketHandler` handles WebSocket connection events and messages.
- **Config**: `WebSocketConfig` registers the WebSocket handler.
- **Client**: `src/main/resources/static/index.html` is a simple test client.
