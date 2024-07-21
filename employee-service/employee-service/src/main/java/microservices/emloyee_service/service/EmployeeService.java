package microservices.emloyee_service.service;

import microservices.emloyee_service.dto.APIResponseDTO;
import microservices.emloyee_service.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    APIResponseDTO getEmployee(Long id);

    List<EmployeeDTO> getAllEmployees();
}
