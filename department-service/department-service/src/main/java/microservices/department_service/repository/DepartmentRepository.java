package microservices.department_service.repository;

import microservices.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDepartmentCode(String code);
}
