Overview
This project is a comprehensive Spring Boot microservices application designed to demonstrate best practices in building scalable, maintainable, and loosely coupled microservices. The system consists of three main microservices, utilizing various Spring Cloud features and technologies to ensure robust communication, configuration management, and service discovery.

Microservices Architecture
1. Employee Service
The Employee Service is responsible for managing employee-related operations. It interacts with the Department Service to fetch department details for each employee.

Technologies Used: Spring Boot, OpenFeign, Spring Data JPA, MySQL
Features:
RESTful API endpoints for CRUD operations on employees.
Uses OpenFeign to call the Department Service and retrieve department information.
Implements Lombok to reduce boilerplate code.
2. Department Service
The Department Service handles all operations related to departments. It provides department data to other services, such as the Employee Service.

Technologies Used: Spring Boot, Spring Data JPA, MySQL
Features:
RESTful API endpoints for CRUD operations on departments.
Database schema defined in MySQL.
Utilizes Lombok for boilerplate code reduction.
3. API Gateway
The API Gateway serves as a single entry point for all client requests, routing them to the appropriate microservice. It also handles request aggregation, rate limiting, and security.

Technologies Used: Spring Cloud Gateway, Spring Cloud Netflix Eureka
Features:
Configures routes to forward requests to Employee and Department Services.
Provides load balancing and API rate limiting.

Service Discovery
Eureka Server
The Eureka Server is used for service discovery, allowing microservices to register themselves and discover other services dynamically.

Technologies Used: Spring Cloud Netflix Eureka
Features:
Manages service registry and discovery.
Ensures that services can locate and communicate with each other seamlessly.
Configuration Management
Config Server
The Config Server centralizes configuration management, allowing external configuration files to be stored in a GitHub repository. This approach simplifies configuration management across different environments.

Technologies Used: Spring Cloud Config Server
Features:
Retrieves configuration properties from a GitHub repository.
Supports versioning and dynamic configuration updates.
Database
MySQL
The application uses MySQL as the primary database for storing employee and department data.

Technologies Used: MySQL
Features:
Database schemas defined for Employee and Department entities.
Configured with Spring Data JPA for ORM-based data access.
Code Quality and Maintenance
Lombok
Lombok is used extensively across the project to minimize boilerplate code, making the codebase cleaner and more maintainable.

Technologies Used: Lombok
Features:
Reduces boilerplate code for getters, setters, constructors, and toString methods.
