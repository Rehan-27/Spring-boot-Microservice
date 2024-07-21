package microservices.emloyee_service.repository;

import microservices.emloyee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {

}
