package microservices.emloyee_service.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import microservices.emloyee_service.dto.APIResponseDTO;
import microservices.emloyee_service.dto.DepartmentDTO;
import microservices.emloyee_service.dto.EmployeeDTO;
import microservices.emloyee_service.dto.OrganizationDTO;
import microservices.emloyee_service.entity.Employee;
import microservices.emloyee_service.exception.ResourceNotFoundException;
import microservices.emloyee_service.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    private WebClient webClient;
    //private APIClient apiClient;
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {

        //converting dto to jpa entity
        Employee employee= modelMapper.map(employeeDTO,Employee.class);

        //saving entity to DB
        Employee savedEmployee= employeeRepository.save(employee);

        //converting jpa entity to dto
        EmployeeDTO savedEmployeeDto= modelMapper.map(savedEmployee,EmployeeDTO.class);

        return savedEmployeeDto;
    }

    @Override
    @CircuitBreaker(name="employee-service", fallbackMethod = "getDefaultFallback")
    public APIResponseDTO getEmployee(Long id) {

        // Find the employee by id and handle the case if not found
        Employee savedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // Fetch department details using Feign client
        //DepartmentDTO departmentDTO = apiClient.getDepartmentByCode(savedEmployee.getDepartmentCode());

        DepartmentDTO departmentDTO=webClient.get()
        .uri("http://localhost:8080/api/departments//getDepartmentByCode/"
        +savedEmployee.getDepartmentCode())
        .retrieve().bodyToMono(DepartmentDTO.class).block();

        OrganizationDTO organizationDto=webClient.get()
        .uri("http://localhost:8083/api/organization/orgByCode/"
        +savedEmployee.getOrganizationCode())
        .retrieve().bodyToMono(OrganizationDTO.class).block();

        // Map the Employee entity to EmployeeDTO
        EmployeeDTO savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDTO.class);

        // Create APIResponseDTO and set the employee and department details
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(savedEmployeeDto);
        apiResponseDTO.setDepartmentDTO(departmentDTO);
        apiResponseDTO.setOrganizationDTO(organizationDto);

        return apiResponseDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList= employeeRepository.findAll();

        List<EmployeeDTO> employeeDtoList= employeeList.stream()
                .map(employee-> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());

        return  employeeDtoList;
    }


    public APIResponseDTO getDefaultFallback(Long id,Exception exception) {

        Employee savedEmployee= employeeRepository.findById(id).get();

//        DepartmentDTO departmentDTO=webClient.get()
//                .uri("http://localhost:8080/api/departments//getDepartmentByCode/"
//                +savedEmployee.getDepartmentCode())
//                .retrieve().bodyToMono(DepartmentDTO.class).block();


        //will return default DTO if dept service is down
        DepartmentDTO departmentDTO=new DepartmentDTO();
        departmentDTO.setDepartmentCode("DEFAULT-FALLBACK-CODE");
        departmentDTO.setDepartmentName("DEFAULT-FALLBACK-NAME");
        departmentDTO.setDepartmentDescription("DEFAULT-FALLBACK-DESCRIPTION");

        if (savedEmployee==null) {
            throw new ResourceNotFoundException("Employee", "id", id);
        }

        EmployeeDTO savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDTO.class);

        APIResponseDTO apiResponseDTO= new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(savedEmployeeDto);
        apiResponseDTO.setDepartmentDTO(departmentDTO);
        return apiResponseDTO;
    }
}
