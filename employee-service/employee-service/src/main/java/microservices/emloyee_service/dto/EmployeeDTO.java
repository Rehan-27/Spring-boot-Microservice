package microservices.emloyee_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Employee data transfer object")
public class EmployeeDTO {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Long id;

    @NotEmpty(message = "Employee name should not be empty.")
    private String firstName;

    @NotEmpty(message = "Employee last-name should not be empty.")
    private String lastName;

    @NotEmpty(message = "Employee Email should not be empty or Null.")
    @Email(message = "Email address should be Valid.")
    private String email;

    @NotEmpty(message = "Employee's department code should not be empty or Null.")
    private String departmentCode;

    @NotEmpty(message = "Employee's organization code should not be empty or Null.")
    private String organizationCode;
}
