package springboot.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;

public interface AssessmentTypeJpaRepository extends JpaRepository<AssessmentTypeJpaEntity, UUID> {
    boolean existsByCode(String code);
}
