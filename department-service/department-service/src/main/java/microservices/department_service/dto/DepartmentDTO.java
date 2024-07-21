package microservices.department_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Department data transfer object")
public class DepartmentDTO {

    @Schema(description = "Unique identifier of the department", example = "1")
    private Long id;

    @Schema(description = "Name of the department")
    @NotEmpty(message = "Department Name should not be null.")
    private String departmentName;

    @Schema(description = "Description of the department")
    private String departmentDescription;

    @Schema(description = "Code of the department")
    @NotEmpty(message = "Department Code should not be null.")
    private String departmentCode;
}
