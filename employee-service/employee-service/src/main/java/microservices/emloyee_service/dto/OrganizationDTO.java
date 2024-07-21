package microservices.emloyee_service.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {

    private Long organizationId;

    @NotEmpty(message = "Organization Code cannot be Null or Empty.")
    private String organizationName;

    private  String organizationDescription;

    @NotEmpty(message = "Organization Code cannot be Null or Empty.")
    private String organizationCode;

    private LocalDateTime createdDate;
}
