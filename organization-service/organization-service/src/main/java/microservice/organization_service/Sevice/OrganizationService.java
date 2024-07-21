package microservice.organization_service.Sevice;

import microservice.organization_service.DTO.OrganizationDTO;

public interface OrganizationService {
    OrganizationDTO createOrganization(OrganizationDTO organizationDto);

    OrganizationDTO getOrgByOrgCode(String orgCode);
}
