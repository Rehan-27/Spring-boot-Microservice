package microservice.organization_service.Repository;


import microservice.organization_service.Entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    Organization findByOrganizationCode(String orgCode);
}
