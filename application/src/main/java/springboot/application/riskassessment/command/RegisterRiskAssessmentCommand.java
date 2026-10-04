package springboot.application.riskassessment.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;

public record RegisterRiskAssessmentCommand(
        EncounterId encounterId,
        RiskLevelId riskLevelId,
        boolean suicidalIdeation,
        boolean suicidePlan,
        boolean suicideIntent,
        boolean selfHarm,
        boolean harmToOthers,
        String riskFactors,
        String protectiveFactors,
        String clinicalActions,
        String observations,
        LocalDateTime assessedAt,
        ProfessionalId assessedBy
) {
    public RegisterRiskAssessmentCommand {
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(riskLevelId, "riskLevelId must not be null");
        Objects.requireNonNull(riskFactors, "riskFactors must not be null");
        Objects.requireNonNull(protectiveFactors, "protectiveFactors must not be null");
        Objects.requireNonNull(clinicalActions, "clinicalActions must not be null");
        Objects.requireNonNull(observations, "observations must not be null");
        Objects.requireNonNull(assessedAt, "assessedAt must not be null");
        Objects.requireNonNull(assessedBy, "assessedBy must not be null");
    }
}
