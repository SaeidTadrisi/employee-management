package com.example.employee.employee_management.repository;

import com.example.employee.employee_management.model.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PositionRepositoryTest {

    @Autowired
    PositionRepository positionRepository;

    @Test
    void testSaveAndFindById() {
        Position position = new Position("Developer", "Develop Software");
        position = positionRepository.save(position);

        Optional<Position> found = positionRepository.findById(position.getId());

        assertTrue(found.isPresent());
        assertEquals("Developer", found.get().getTitle());
        assertEquals("Develop Software", found.get().getResponsibilities());
        assertEquals(position.getId(), found.get().getId());
    }

    @Test
    void testExistsByEmail() {
        Position position = new Position("Developer", "Develop Software");
        positionRepository.save(position);

        boolean found = positionRepository.existsByTitle("Developer");
        assertTrue(found);
    }

    @Test
    void testDelete() {
        Position position = new Position("Developer", "Develop Software");
        position = positionRepository.save(position);

        positionRepository.delete(position);

        Optional<Position> found = positionRepository.findById(position.getId());
        assertTrue(found.isEmpty());
    }

    @Test
    void testUpdateDepartment() {
        Position position = new Position("Developer", "Develop Software");
        position = positionRepository.save(position);

        position.setTitle("Tester");
        position = positionRepository.save(position);

        Optional<Position> found = positionRepository.findById(position.getId());
        assertTrue(found.isPresent());
        assertEquals("Tester", found.get().getTitle());
    }
}
