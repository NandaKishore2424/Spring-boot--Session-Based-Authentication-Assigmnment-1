# Fitness Tracking App - System Design Assignment

## Project Overview

A Spring Boot REST API for a fitness tracking application that handles user registration, authentication, and secure dashboard access.

## Components and Layers

### 1. Presentation Layer

- **AuthController**: Handles registration and login endpoints
- **UserController**: Manages user profile and dashboard access

### 2. Service Layer

- **UserService**: Business logic for user operations

### 3. Data Access Layer

- **UserRepository**: JPA repository for database operations
- **User Entity**: Database entity with fitness-specific fields

### 4. Security Layer

- **JWT Token Provider**: Generates and validates JWT tokens
- **JWT Authentication Filter**: Intercepts requests and validates tokens
- **Security Configuration**: Configures Spring Security

### 5. DTOs (Data Transfer Objects)

- **UserRegistrationDTO**: Registration request data
- **LoginDTO**: Login credentials
- **AuthResponseDTO**: Authentication response with token
- **UserProfileDTO**: User profile information

## REST API Structure

### Authentication Endpoints (Public)

```
POST /api/auth/register - User registration
POST /api/auth/login - User login
```

### Protected Endpoints (Requires JWT Token)

```
GET /api/user/profile - Get user profile
GET /api/user/dashboard - Access user dashboard
```

## Security Implementation

### JWT Token-based Authentication

- Tokens generated upon successful login/registration
- All protected endpoints require valid JWT token in Authorization header
- Format: `Authorization: Bearer <token>`

### Password Security

- BCrypt password encoding
- Minimum 6 characters requirement

### Email Uniqueness

- Database constraint ensures unique email addresses
- Validation occurs during registration

## How to Run

1. Build the project:

```bash
./gradlew build
```

2. Run the application:

```bash
./gradlew bootRun
```

3. Access H2 Database Console (for testing):

```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (empty)
```

## API Usage Examples

### Register User

```bash
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "phone": "1234567890",
    "age": 25,
    "height": 175.5,
    "weight": 70.0,
    "gender": "MALE",
    "fitnessGoal": "MUSCLE_BUILDING"
}
```

### Login

```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "email": "john.doe@example.com",
    "password": "password123"
}
```

### Access Dashboard (with token)

```bash
GET http://localhost:8080/api/user/dashboard
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
```
