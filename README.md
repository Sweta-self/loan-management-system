# Loan Management System-Overview
This is a secure Loan Management System built using Spring boot that demonstrates real world backend concepts like authentication,authorization,role based access,entity ownership and soft delete.

## Tech Stack
- Java 17
- Spring Boot
- Spring Security
- Hibernate/JPA
- MySQL
- Maven
- Swagger/OpenAPI

## Features
- Secure user authentication
- Role based access control
- Automatic customer creation during user registration
- Customer-specific loan Ownership
- Admin-controlled loan approval workflow
- Pagination and sorting support
- Advanced search and filtering using JPA Specifications
- Global Exception handling 
- Soft delete for data safety

## Application Architecture
The application follows a layered architecture:
- Controller Layer
- Service Layer
- Repository Layer
- Database


## Entity Relatinships

- One User -> One Customer
- One Customer -> Multiple Loans

## Authentication Flow
1. User logs in using email and password
2. AuthenticationManager validates Credentials
3. JWT Token is generated
4. Token is returned to the client
5. Client sends token in Authorization header
6. JWT filter validates token for every request

## Authorization Rules
|Role|Access|
|----|------|
|USER|View and update own loans|
|ADMIN|View all loans,approve or reject loans|

Authorization is enforced using:
- JWT roles
- @PreAuthorize annotations

## Loan Status Workflow
- Loan is created with status 'CREATED'
- Only ADMIN can update loan status
- Status transitions:
- CREATED -> APPROVED
- CREATED -> REJECTED

## Ownership Validation
- A logged-in USER can access only their own customer and loan data
- Ownership is validated using User -> Customer -> Loan relationship
- Prevents unauthorized access even with valid tokens

## Soft Delete Strategy
- Loans are not deleted permanently
- A `deleted` flag is used
- All queries exclude deleted records

## API Documentation
Swagger UI is enabled for API testing.
URL: http://localhost:8080/swagger-ui/index.html

## Running the Application
1. Clone the repository
2. Configure MySQL database credentials
3. Run the spring boot application
4. Open Swagger UI to test APIs

  
  
