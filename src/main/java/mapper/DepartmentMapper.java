package mapper;

import com.example.employee.employee_management.dto.DepartmentDTO;
import com.example.employee.employee_management.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDTO(Department department){
        if (department == null){
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(departmentDTO.getName());
        return departmentDTO;
    }

    public Department toEntity(DepartmentDTO departmentDTO){
        if (departmentDTO == null){
            return null;
        }
        Department department = new Department();
        department.setName(departmentDTO.getName());
        return department;
    }
}
