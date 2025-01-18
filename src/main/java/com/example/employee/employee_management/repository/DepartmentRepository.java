package com.example.employee.employee_management.repository;

import com.example.employee.employee_management.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query ("SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id")
    Optional<Department> findDepartmentByIdWithEmployees(@Param("id") Long id);
}
