package com.example.employee.employee_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees", schema = "employee_management")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    private Double salary;


}
