package microservices.department_service.service;

import microservices.department_service.dto.DepartmentDTO;
import microservices.department_service.entity.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentByCode(String code);

    List<DepartmentDTO> getAllDepartments();
}
