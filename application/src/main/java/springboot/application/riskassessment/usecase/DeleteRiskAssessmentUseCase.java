package springboot.application.riskassessment.usecase;

import java.time.LocalDateTime;

import springboot.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import springboot.domain.riskassessment.event.RiskAssessmentDeletedEvent;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class DeleteRiskAssessmentUseCase {
    private final RiskAssessmentRepository repository;
    public DeleteRiskAssessmentUseCase(RiskAssessmentRepository repository) { this.repository = repository; }

    public RiskAssessmentDeletedEvent execute(RiskAssessmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RiskAssessmentNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new RiskAssessmentDeletedEvent(id, LocalDateTime.now());
    }
}
