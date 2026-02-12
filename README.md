**TASK MANAGEMENT SYSTEM**

A robust REST API developed with Java and Spring Boot, designed to streamline task organization through a secure, layered architecture.


**PROJECT OVERVIEW**

This project provides a comprehensive backend solution for managing tasks, users, and priorities. By utilizing an N-tier architecture, the application ensures a clear separation of concerns, making the codebase maintainable, scalable, and secure.

**Technical Stack**

**Language:** Java

**Framework:** Spring Boot (REST API) and Maven

**Security: **Spring Security (Role-based access control)

**Data Access:** Spring Data JPA

**Database:** MySQL




**ARCGITECTURE AND PACKAGE INSTRUCTOR**

The project is organized into specific packages to follow industry best practices:

**Controller**: Defines the REST API endpoints and handles incoming client requests.

**Service**: Contains the core business logic and processes.

**Repository**: Interfaces for database communication and CRUD operations.

**Entity:** Database models representing the core objects of the system.





**KEY FEATURES**

Secure Authentication: Integrated Spring Security to protect user data.
Full CRUD Functionality: Create, Read, Update, and Delete tasks via REST endpoints.
Data Validation: Using DTOs to sanitize and validate all user inputs.
Priority Tracking: Categorize tasks using strictly typed Enums.



Email Notifications: Implementing an automated reminder system for approaching deadlines.
Dark Mode: Developing a modern, user-friendly Dark Mode toggle for the frontend.
File Attachments: Allowing users to upload documents directly to specific tasks.
