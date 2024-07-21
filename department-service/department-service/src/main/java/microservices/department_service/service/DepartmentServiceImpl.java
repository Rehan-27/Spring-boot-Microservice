package microservices.department_service.service;

import lombok.AllArgsConstructor;
import microservices.department_service.dto.DepartmentDTO;
import microservices.department_service.entity.Department;
import microservices.department_service.exception.ResourceNotFoundException;
import microservices.department_service.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        //converting DTO to JPA entity
        Department department= modelMapper.map(departmentDTO,Department.class);

        //saving tp DB
        Department savedDepartment= departmentRepository.save(department);

        //converting JPA entity to DTO again
        DepartmentDTO savedDepartmentDto=modelMapper.map(savedDepartment,DepartmentDTO.class);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String code){

        Department savedDepartment= departmentRepository.findByDepartmentCode(code);
        if (savedDepartment == null) {
            throw new ResourceNotFoundException("Department","code",code);
        }
        DepartmentDTO savedDepartmentDto=modelMapper.map(savedDepartment,DepartmentDTO.class);
        return savedDepartmentDto;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {

        List<Department> departmentList = departmentRepository.findAll();
        System.out.println(departmentList);
        List<DepartmentDTO> departmentDtoList = departmentList.stream()
                .map(department -> modelMapper.map(department,DepartmentDTO.class)) // Assuming a constructor that takes Department
                .collect(Collectors.toList());
        return departmentDtoList;
    }
}
