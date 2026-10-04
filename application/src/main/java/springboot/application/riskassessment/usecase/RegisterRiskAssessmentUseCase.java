package springboot.application.riskassessment.usecase;

import springboot.application.riskassessment.command.RegisterRiskAssessmentCommand;
import springboot.application.riskassessment.dto.RiskAssessmentResponse;
import springboot.domain.riskassessment.model.aggregate.RiskAssessment;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class RegisterRiskAssessmentUseCase {
    private final RiskAssessmentRepository repository;
    public RegisterRiskAssessmentUseCase(RiskAssessmentRepository repository) { this.repository = repository; }

    public RiskAssessmentResponse execute(RegisterRiskAssessmentCommand command) {
        RiskAssessment aggregate = RiskAssessment.register(
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
        RiskAssessment saved = repository.save(aggregate);
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
