package springboot.application.riskassessment.usecase;

import springboot.application.riskassessment.dto.RiskAssessmentResponse;
import springboot.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class GetRiskAssessmentByIdUseCase {
    private final RiskAssessmentRepository repository;
    public GetRiskAssessmentByIdUseCase(RiskAssessmentRepository repository) { this.repository = repository; }

    public RiskAssessmentResponse execute(RiskAssessmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RiskAssessmentNotFoundApplicationException(id.value().toString()));
        return new RiskAssessmentResponse(
                aggregate.id().value(),
                aggregate.encounterId().value(),
                aggregate.riskLevelId().value(),
                aggregate.suicidalIdeation(),
                aggregate.suicidePlan(),
                aggregate.suicideIntent(),
                aggregate.selfHarm(),
                aggregate.harmToOthers(),
                aggregate.riskFactors(),
                aggregate.protectiveFactors(),
                aggregate.clinicalActions(),
                aggregate.observations(),
                aggregate.assessedAt(),
                aggregate.assessedBy().value());
    }
}
