package springboot.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

public interface RiskAssessmentJpaRepository extends JpaRepository<RiskAssessmentJpaEntity, UUID> {

}
