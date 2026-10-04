package springboot.domain.riskassessment.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.riskassessment.model.aggregate.RiskAssessment;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;

public interface RiskAssessmentRepository {
    RiskAssessment save(RiskAssessment aggregate);
    Optional<RiskAssessment> findById(RiskAssessmentId id);
    List<RiskAssessment> findAll();

    void delete(RiskAssessment aggregate);
}
