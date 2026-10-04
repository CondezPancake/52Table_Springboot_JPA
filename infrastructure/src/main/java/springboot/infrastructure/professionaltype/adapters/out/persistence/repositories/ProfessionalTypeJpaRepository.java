package springboot.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

public interface ProfessionalTypeJpaRepository extends JpaRepository<ProfessionalTypeJpaEntity, UUID> {

}
