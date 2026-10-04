package springboot.application.riskassessment.usecase;

import springboot.application.riskassessment.command.UpdateRiskAssessmentCommand;
import springboot.application.riskassessment.dto.RiskAssessmentResponse;
import springboot.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class UpdateRiskAssessmentUseCase {
    private final RiskAssessmentRepository repository;
    public UpdateRiskAssessmentUseCase(RiskAssessmentRepository repository) { this.repository = repository; }

    public RiskAssessmentResponse execute(UpdateRiskAssessmentCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new RiskAssessmentNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.encounterId(),
                command.riskLevelId(),
                command.suicidalIdeation(),
                command.suicidePlan(),
                command.suicideIntent(),
                command.selfHarm(),
                command.harmToOthers(),
                command.riskFactors(),
                command.protectiveFactors(),
                command.clinicalActions(),
                command.observations(),
                command.assessedAt(),
                command.assessedBy());
        var saved = repository.save(aggregate);
        return new RiskAssessmentResponse(
                saved.id().value(),
                saved.encounterId().value(),
                saved.riskLevelId().value(),
                saved.suicidalIdeation(),
                saved.suicidePlan(),
                saved.suicideIntent(),
                saved.selfHarm(),
                saved.harmToOthers(),
                saved.riskFactors(),
                saved.protectiveFactors(),
                saved.clinicalActions(),
                saved.observations(),
                saved.assessedAt(),
                saved.assessedBy().value());
    }
}
