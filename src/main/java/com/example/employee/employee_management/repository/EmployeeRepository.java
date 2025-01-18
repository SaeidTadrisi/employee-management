package com.example.employee.employee_management.repository;

import com.example.employee.employee_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e " +
            "JOIN FETCH e.department " +
            "JOIN FETCH e.position " +
            "WHERE e.id = :id")
    Optional<Employee> findEmployeeByIdWithDepartmentAndPosition(@Param("id") Long id);
}
