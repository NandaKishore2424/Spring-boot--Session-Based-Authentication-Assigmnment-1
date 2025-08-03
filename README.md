# Fitness Tracking App - Session-Based Authentication Assignment

## Project Overview

A Spring Boot web application for fitness tracking that uses session-based authentication (JSESSIONID cookie) to secure user dashboards, profiles, and workout plans.

## Components and Layers

### 1. Presentation Layer

- **AuthController**: Handles registration and login endpoints
- **UserController**: Manages user profile and dashboard access
- **HTML Pages**: login.html, register.html, dashboard.html, profile.html, workout-plans.html, logs.html, etc.

### 2. Service Layer

- **UserService**: Business logic for user operations
- **UserDetailsServiceImpl**: Loads user details for authentication

### 3. Data Access Layer

- **UserRepository**: JPA repository for database operations
- **User Entity**: Database entity with fitness-specific fields

### 4. Security Layer

- **SecurityConfig**: Configures Spring Security for session-based authentication
- **Session Management**: JSESSIONID cookie-based session tracking
- **Form Login**: Username/password authentication with redirect

### 5. DTOs (Data Transfer Objects)

- **UserRegistrationDTO**: Registration request data
- **LoginDTO**: Login credentials
- **UserProfileDTO**: User profile information
- **WeeklyReportDTO**: Fitness report data

## Application Flow & Endpoints

### Public Endpoints (No Login Required)

```
GET /login.html           - Login page
GET /register.html        - Registration page
POST /login               - Login form submission
POST /api/auth/register   - User registration
GET /index.html           - Home/redirect page
```

### Protected Endpoints (Require Login/Session)

```
GET /dashboard.html       - User dashboard
GET /profile.html         - User profile
GET /workout-plans.html   - Workout plans
GET /logs.html            - Workout logs
GET /logout.html          - Logout page (triggers logout)
```

## Security Implementation

### Session-Based Authentication

- Spring Security manages sessions using JSESSIONID cookie
- Form-based login with username/password fields
- Session invalidation on logout
- Protected resources require a valid session (redirects to login if not authenticated)

### Password Security

- BCrypt password encoding
- Password stored securely in database
- Custom UserDetailsService for authentication

### Authorization

- All dashboard and user data endpoints require authentication
- Redirects to login page when accessing protected resources without a session

## How to Run

1. Build the project:

```bash
./gradlew build
```

2. Run the application:

```bash
./gradlew bootRun
```

3. Access the application:

```
URL: http://localhost:8080
```

4. Access H2 Database Console (for testing):

```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (empty)
```

## Usage Examples

### Register a New User

1. Go to http://localhost:8080/register.html
2. Fill in the registration form and submit
3. You will be redirected to the login page

### Login

1. Go to http://localhost:8080/login.html
2. Enter your email and password
3. Upon successful login, you will be redirected to the dashboard

### Access Protected Resources

1. After login, you can access:
   - Dashboard: http://localhost:8080/dashboard.html
   - Profile: http://localhost:8080/profile.html
   - Workout Plans: http://localhost:8080/workout-plans.html
   - Logs: http://localhost:8080/logs.html

### Logout

1. Click "Logout" in the navigation menu
2. You will be redirected to the login page and your session will be invalidated

## Key Security Files

- **SecurityConfig.java**: Main security configuration (session-based)
- **UserDetailsServiceImpl.java**: User authentication implementation
- **login.html/register.html**: Authentication forms
