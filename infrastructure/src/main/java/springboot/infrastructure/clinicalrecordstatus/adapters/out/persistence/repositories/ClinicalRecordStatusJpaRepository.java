package springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;

public interface ClinicalRecordStatusJpaRepository extends JpaRepository<ClinicalRecordStatusJpaEntity, UUID> {
    boolean existsByCode(String code);
}
