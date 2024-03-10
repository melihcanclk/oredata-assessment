# Mini Banking Application

Welcome to the Mini Banking Application! This project allows users to manage their accounts, perform transactions, and view transaction history. The application consists of two main parts: the backend, built with Spring Boot and Java, and the frontend, a React application using Vite.

## Backend (Spring Boot - Java)

### Project Overview

The backend of the Mini Banking Application is responsible for handling user registration, authentication, account management, and transaction operations. It exposes RESTful endpoints to support these functionalities.

### Database

This application utilizes PostgreSQL as the database for storing user information, account details, and transaction records. Make sure to have PostgreSQL installed and running before setting up the backend.

### Configuration

Update the `application.properties` file in the backend with your PostgreSQL database configuration. Below are sample configurations:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password

# Hibernate
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto = update
```

### Model Definitions

#### User Model
- **id (UUID):** A unique identifier for the user.
- **username (String):** The user's chosen username.
- **password (String):** The user's encrypted password.
- **email (String):** The user's email address.
- **createdAt (LocalDateTime):** The user’s create date.
- **updatedAt (LocalDateTime):** The user’s update date.

#### Account Model
- **id (UUID):** A unique identifier for the account.
- **number (String):** A unique account number.
- **name (String):** A unique account name.
- **balance (BigDecimal):** The current balance of the account.
- **createdAt (LocalDateTime):** The account’s create date.
- **updatedAt (LocalDateTime):** The account’s update date.

#### Transaction Model
- **id (Long):** A unique identifier for the transaction.
- **from (Account):** The account ID from which money is being transferred.
- **to (Account):** The account ID to which money is being transferred.
- **amount (BigDecimal):** The amount of money being transferred.
- **transactionDate (LocalDateTime):** The date and time when the transaction was initiated.
- **status (Enum):** The status of the transaction (e.g., "SUCCESS", "FAILED").

### RESTful Endpoints

#### User Management

1. **Register a New User**
   - `POST /api/users/register`
   - Registers a new user with username, password, and email.

2. **User Login**
   - `POST /api/users/login`
   - Authenticates a user and returns a JWT for accessing protected endpoints.

#### Account Operations

1. **Create Account**
   - `POST /api/accounts`
   - Creates a new account for the authenticated user.

2. **Search Accounts**
   - `POST /api/accounts`
   - Search accounts for the authenticated user.
   - Accounts should be filterable on number and name.

3. **Update Account**
   - `PUT /api/accounts/{id}`
   - Updates the selected account for the authenticated user.

4. **Delete Account**
   - `DELETE /api/accounts/{id}`
   - Deletes the selected account for the authenticated user.

5. **View Account Details**
   - `GET /api/accounts/{id}`
   - Retrieves details of a specific account, including the balance. Access is restricted to the account owner.

#### Transaction Operations

1. **Initiate Money Transfer**
   - `POST /api/transactions/transfer`
   - Transfers money from one account to another. Transfers can occur simultaneously.

2. **View Transaction History**
   - `GET /api/transactions/account/{accountId}`
   - Retrieves the transaction history for a specified account. Access is restricted to the account owner.

### Swagger Documentation

The API is documented using Swagger. Access the Swagger UI at `/swagger-ui.html` after starting the application.

## Frontend (React - Vite)

### Project Overview

The frontend of the Mini Banking Application is built with React and Vite. It provides user authentication forms, banking features, API integration, state management, and routing/navigation.

### User Authentication

- Implement login and registration forms.
- Use JWT tokens for handling user authentication and maintaining session state.

### Banking Features

- Account creation and management (CRUD).
- Search accounts using query parameters on the frontend.
- Display account balance and details.
- Transfer money between accounts with form validation and feedback on transaction success or failure.
- View transaction history.

### API Integration

- Use Axios or Fetch API to interact with the backend, ensuring secure transmission of data, especially for authentication and money transfer operations.

### State Management

- Apply Context API or Redux or Zustand, etc., for managing application state, focusing on user authentication status, account information, and transaction history.

### Routing and Navigation

- Utilize React Router for navigating between different pages like login, account details, transfer money, and transaction history.

## Getting Started

1. **Backend Setup:**
   - Clone the backend repository.
   - Create database named "cilekbank" as given in application.properties file in springboot application.
   - Change username and password according to your postgresql database.
   - Configure the database and application properties.
   - Run the Spring Boot application.

2. **Frontend Setup:**
   - Clone the frontend repository.
   - Install dependencies using `npm install`.
   - Configure API endpoints.
   - Run the React application with `npm run dev`.

Explore the Mini Banking Application and enjoy managing your finances and balances seamlessly! Feel free to contribute 😃

**Note:** Ensure that both the backend and frontend are running concurrently for the full application experience.
