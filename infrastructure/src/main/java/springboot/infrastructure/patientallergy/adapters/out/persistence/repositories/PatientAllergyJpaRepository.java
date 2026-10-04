package springboot.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

public interface PatientAllergyJpaRepository extends JpaRepository<PatientAllergyJpaEntity, UUID> {

}
