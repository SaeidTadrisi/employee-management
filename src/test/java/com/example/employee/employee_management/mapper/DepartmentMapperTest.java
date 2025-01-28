package com.example.employee.employee_management.mapper;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.model.Department;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentMapperTest {

    private final DepartmentMapper departmentMapper = new DepartmentMapper();

    @Test
    void toDTO_ShouldMapDepartmentToDTO() {
        Department department = new Department("HR");
        department.setId(1L);

        DepartmentDTO dto = departmentMapper.toDTO(department);

        assertNotNull(dto);
        assertEquals("HR", dto.getName());
        assertEquals(1L, dto.getId());
    }

    @Test
    void toEntity_ShouldMapDTOToDepartment() {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("Finance");

        Department department = departmentMapper.toEntity(dto);

        assertNotNull(department);
        assertEquals("Finance", department.getName());
    }

    @Test
    void toDTOList_ShouldMapListOfDepartmentsToDTOs() {
        Department hr = new Department("HR");
        Department finance = new Department("Finance");

        List<Department> departments = List.of(hr, finance);

        List<DepartmentDTO> dtoList = departmentMapper.toDTOList(departments);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals("HR", dtoList.get(0).getName());
        assertEquals("Finance", dtoList.get(1).getName());
    }

    @Test
    void toDTOList_ShouldReturnEmptyListWhenInputIsNull() {
        List<DepartmentDTO> dtoList = departmentMapper.toDTOList(null);

        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }
}
