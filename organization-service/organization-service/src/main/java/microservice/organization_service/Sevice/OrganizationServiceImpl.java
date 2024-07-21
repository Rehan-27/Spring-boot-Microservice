package microservice.organization_service.Sevice;

import lombok.AllArgsConstructor;
import microservice.organization_service.DTO.OrganizationDTO;
import microservice.organization_service.Entity.Organization;
import microservice.organization_service.Exception.OrganizationAlreadyExistsException;
import microservice.organization_service.Exception.ResourceNotFoundException;
import microservice.organization_service.Repository.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements  OrganizationService{

    private OrganizationRepository organizationRepository;

    private ModelMapper modelMapper;

    @Override
    public OrganizationDTO createOrganization(OrganizationDTO organizationDto) {

        Organization organization=modelMapper.map(organizationDto,Organization.class);

        if(organization.getOrganizationCode()!=null)
            throw new OrganizationAlreadyExistsException("Organization with org-code "+organization.getOrganizationCode()+" already exists");        //SAVING TO DB
        Organization savedOrganization= organizationRepository.save(organization);
        OrganizationDTO savedOrganizationDto= modelMapper.map(savedOrganization,OrganizationDTO.class);
        return savedOrganizationDto;
    }

    @Override
    public OrganizationDTO getOrgByOrgCode(String orgCode) {
        //checking in DB
        Organization organizationOptional= organizationRepository.findByOrganizationCode(orgCode);

        if(organizationOptional==null)
            throw new ResourceNotFoundException("Organization","organization code",orgCode);

        OrganizationDTO orgDtoFromDB= modelMapper.map(organizationOptional,OrganizationDTO.class);
        return orgDtoFromDB;
    }
}
