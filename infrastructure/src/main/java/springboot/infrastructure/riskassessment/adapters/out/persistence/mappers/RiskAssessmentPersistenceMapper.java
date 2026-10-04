package springboot.infrastructure.riskassessment.adapters.out.persistence.mappers;

import springboot.domain.riskassessment.model.aggregate.RiskAssessment;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;
import springboot.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

public class RiskAssessmentPersistenceMapper {
    public RiskAssessmentJpaEntity toJpa(RiskAssessment domain) {
        if (domain == null) { return null; }
        RiskAssessmentJpaEntity jpa = new RiskAssessmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEncounterId(domain.encounterId().value());
        jpa.setRiskLevelId(domain.riskLevelId().value());
        jpa.setSuicidalIdeation(domain.suicidalIdeation());
        jpa.setSuicidePlan(domain.suicidePlan());
        jpa.setSuicideIntent(domain.suicideIntent());
        jpa.setSelfHarm(domain.selfHarm());
        jpa.setHarmToOthers(domain.harmToOthers());
        jpa.setRiskFactors(domain.riskFactors());
        jpa.setProtectiveFactors(domain.protectiveFactors());
        jpa.setClinicalActions(domain.clinicalActions());
        jpa.setObservations(domain.observations());
        jpa.setAssessedAt(domain.assessedAt());
        jpa.setAssessedBy(domain.assessedBy().value());

        return jpa;
    }

    public RiskAssessment toDomain(RiskAssessmentJpaEntity jpa) {
        if (jpa == null) { return null; }
        return RiskAssessment.restore(
                new RiskAssessmentId(jpa.getId()),
                new EncounterId(jpa.getEncounterId()),
                new RiskLevelId(jpa.getRiskLevelId()),
                jpa.isSuicidalIdeation(),
                jpa.isSuicidePlan(),
                jpa.isSuicideIntent(),
                jpa.isSelfHarm(),
                jpa.isHarmToOthers(),
                jpa.getRiskFactors(),
                jpa.getProtectiveFactors(),
                jpa.getClinicalActions(),
                jpa.getObservations(),
                jpa.getAssessedAt(),
                new ProfessionalId(jpa.getAssessedBy()));
    }
}
