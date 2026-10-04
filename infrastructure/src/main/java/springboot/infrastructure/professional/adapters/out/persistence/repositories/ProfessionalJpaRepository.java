package springboot.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;

public interface ProfessionalJpaRepository extends JpaRepository<ProfessionalJpaEntity, UUID> {

}
