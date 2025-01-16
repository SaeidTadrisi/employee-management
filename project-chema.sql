DROP SCHEMA IF EXISTS employee_management;
CREATE SCHEMA employee_management;

CREATE TABLE employee_management.departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE employee_management.positions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    responsibilities TEXT
);

CREATE TABLE employee_management.employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    position_id INT,
    department_id INT,
    salary DECIMAL(10, 2),
    FOREIGN KEY (position_id) REFERENCES employee_management.positions(id),
    FOREIGN KEY (department_id) REFERENCES employee_management.departments(id)
);