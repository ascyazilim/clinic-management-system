# 🏥 Clinic Management System

A backend application for managing clinical operations such as patient records and related data.  
This project is developed using **Spring Boot** and follows **RESTful API** principles.

---

## 🚀 Features

- Patient management (create, list, search)
- Search patients by first name or last name
- Pagination support for large patient datasets
- Validation for request payloads
- Clean layered architecture (Controller, Service, Repository)
- PostgreSQL database integration

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.4.x**
- **Spring Data JPA (Hibernate)**
- **Spring Validation**
- **PostgreSQL**
- **Lombok**
- **Maven**

---

## 🧱 Project Architecture

The project follows a layered architecture:

controller -> service -> repository -> database

### Package Structure

com.asc.clinicms
├── patients
│ ├── controller
│ ├── service
│ ├── repo
│ ├── entity
│ └── dto
├── common
│ ├── response
│ └── exception
└── ClinicMsApplication

---

## 📦 Patient Module Overview

### Patient Entity
Represents a patient in the system with basic identification and contact information.

### Patient Repository
- Find patient by TC number
- Search by first name or last name
- Supports pagination using `Page` and `Pageable`

### Patient Service
Contains business logic and validation rules.

### Patient Controller
Exposes REST endpoints for patient-related operations.

---

## 🔍 Sample Endpoints

### Create Patient
POST /api/patients


### Get All Patients (Paginated)
GET /api/patients?page=0&size=10

pgsql
Kodu kopyala

### Search Patient by Name
GET /api/patients/search?keyword=ali&page=0&size=10

---

## ✅ Validation

Request payloads are validated using Spring Validation annotations such as:
- `@NotBlank`
- `@Size`
- `@Pattern`

Invalid requests return meaningful validation error responses.

---

## 🗄️ Database Configuration

The application uses **PostgreSQL** as the primary database.

Example configuration (`application.yml`):

```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/clinic_db
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
▶️ Running the Application
Clone the repository


git clone https://github.com/your-username/clinic-management-system.git
Configure PostgreSQL connection

Run the application

mvn spring-boot:run
Application will start on:

http://localhost:8080
🔮 Future Improvements
Appointment management

Doctor and staff modules

Authentication and authorization (Spring Security + JWT)

Transition to microservices architecture

Docker and Docker Compose support

API Gateway integration

👨‍💻 Author
Developed by Ali Cebecioğlu

Java Backend Developer

Spring Boot & Microservices

PostgreSQL, REST APIs
