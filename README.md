# Employee Management System

## Overview
This is a Spring Boot-based Employee Management System that allows managing employees, departments, and positions with CRUD operations. The application follows a clean architecture pattern and is secured with Spring Security.

## Features
- **Employee Management:** Create, update, delete, and retrieve employees.
- **Department Management:** Assign employees to departments and manage department details.
- **Position Management:** Assign positions to employees and manage position details.
- **Testing:** Comprehensive unit and integration tests.

## Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA & Hibernate**
- **H2/MySQL** (Database)
- **JUnit 5 & Mockito** (Testing)
- **Spring Boot Test**

## Setup and Installation

### Prerequisites
Ensure you have the following installed:
- Java 17+
- Maven
- MySQL (if using a production database)

### Clone the Repository
```sh
git clone https://github.com/SaeidTadrisi/employee-management.git
cd employee-management
```

### Run the Application
```sh
mvn spring-boot:run
```

## API Endpoints

### Employee APIs
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/employees/` | Create a new employee |
| GET | `/api/employees/{id}` | Get an employee by ID |
| PUT | `/api/employees/{id}` | Update employee details |
| DELETE | `/api/employees/{id}` | Delete an employee |
| PATCH | `/api/employees/{employeeId}/assign-department/{departmentId}` | Assign a department to an employee |
| PATCH | `/api/employees/{employeeId}/assign-position/{positionId}` | Assign a position to an employee |

### Department APIs
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/departments` | Create a new department |
| GET | `/api/departments/{id}` | Get a department by ID |
| PUT | `/api/departments/{id}` | Update department details |
| DELETE | `/api/departments/{id}` | Delete a department |

### Position APIs
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/positions` | Create a new positions |
| GET | `/api/positions/{id}` | Get a positions by ID |
| PUT | `/api/positions/{id}` | Update positions details |
| DELETE | `/api/positions/{id}` | Delete a positions |

## Testing
Run the tests with:
```sh
mvn test
```
This project includes unit tests for:
- **Controllers** (Command & Query Controllers)
- **Services** (Command & Query Services)
- **Repositories**
- **DTO Validations**
- **Mappers**


## Future Improvements
- Implement Spring Security.
- Implement JWT-based authentication.
- Add Role-Based Access Control (RBAC).
- Implement API rate limiting.
- Add caching for frequently accessed data.

## License
This project is licensed under the MIT License.

## Contact
For any inquiries, please contact [saeid.tadrisi@gmail.com].

