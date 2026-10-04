package springboot.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateRiskAssessmentRequest(
        @NotNull(message = "encounterId is required")
        UUID encounterId,

        @NotNull(message = "riskLevelId is required")
        UUID riskLevelId,

        @NotNull(message = "suicidalIdeation is required")
        Boolean suicidalIdeation,

        @NotNull(message = "suicidePlan is required")
        Boolean suicidePlan,

        @NotNull(message = "suicideIntent is required")
        Boolean suicideIntent,

        @NotNull(message = "selfHarm is required")
        Boolean selfHarm,

        @NotNull(message = "harmToOthers is required")
        Boolean harmToOthers,

        @NotNull(message = "riskFactors is required")
        String riskFactors,

        @NotNull(message = "protectiveFactors is required")
        String protectiveFactors,

        @NotNull(message = "clinicalActions is required")
        String clinicalActions,

        @NotNull(message = "observations is required")
        String observations,

        @NotNull(message = "assessedAt is required")
        LocalDateTime assessedAt,

        @NotNull(message = "assessedBy is required")
        UUID assessedBy
) {
}
