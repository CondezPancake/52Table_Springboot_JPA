package springboot.infrastructure.clinicalrecord.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

public interface ClinicalRecordJpaRepository extends JpaRepository<ClinicalRecordJpaEntity, UUID> {

}
