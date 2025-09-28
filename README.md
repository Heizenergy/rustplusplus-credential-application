# Rustplusplus Credential Application (Spring Boot)

This project rewrites the original Electron/Vue credential application as a Java Spring Boot REST API. It manages credential access requests for the [rustplusplus](https://github.com/alexemanuelol/rustplusplus) Discord bot by providing endpoints to submit, review, and delete requests.

## Features

- Submit new credential requests with validation for email, Discord tag, Steam ID, and justification.
- Filter existing requests by their current status (pending, approved, rejected).
- Update request status or remove requests entirely.
- Centralized exception handling with helpful error responses.
- In-memory storage for easy local development (swap for a persistent store as needed).

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+

### Run the Application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

### Run Tests

```bash
mvn test
```

## API Overview

| Method | Endpoint                  | Description                                   |
|--------|---------------------------|-----------------------------------------------|
| POST   | `/api/credentials`        | Create a new credential request.              |
| GET    | `/api/credentials`        | List requests, optionally filtered by status. |
| PUT    | `/api/credentials/{id}/status` | Update the status of a specific request.  |
| DELETE | `/api/credentials/{id}`   | Delete a credential request.                  |

### Sample Request Payloads

**Create request**

```http
POST /api/credentials
Content-Type: application/json

{
  "email": "player@example.com",
  "discordTag": "Player#1234",
  "steamId": "76561198000000000",
  "justification": "I maintain the production bot"
}
```

**Update status**

```http
PUT /api/credentials/1/status
Content-Type: application/json

{
  "status": "APPROVED"
}
```

Validation errors and missing resources return structured JSON responses describing the issue.

## Next Steps

- Replace the in-memory storage with a persistent data store.
- Add authentication/authorization for administrative actions.
- Build a UI (web or desktop) that consumes the REST API.
