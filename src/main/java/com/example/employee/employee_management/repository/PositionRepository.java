package com.example.employee.employee_management.repository;

import com.example.employee.employee_management.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p FROM Position p JOIN FETCH p.employees WHERE p.id = :id")
    Optional<Position> findPositionByIdWithEmployees(@Param("id") Long id);

    boolean existsByTitle(String title);
}
