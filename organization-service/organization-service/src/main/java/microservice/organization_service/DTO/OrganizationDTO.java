package microservice.organization_service.DTO;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
