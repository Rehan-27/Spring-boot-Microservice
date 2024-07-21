package microservice.organization_service.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.organization_service.DTO.OrganizationDTO;
import microservice.organization_service.Sevice.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/organization")
@AllArgsConstructor
@Validated
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping("/createOrg")
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody @Valid OrganizationDTO organizationDto){
        OrganizationDTO respOrganizationDto= organizationService.createOrganization(organizationDto);
        return new ResponseEntity<>(respOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("/orgByCode/{orgCode}")
    public ResponseEntity<OrganizationDTO> getOrgByCode(@PathVariable String orgCode){
        OrganizationDTO organizationDtoFromDB= organizationService.getOrgByOrgCode(orgCode);
        return new ResponseEntity<>(organizationDtoFromDB, HttpStatus.OK);
    }
}
