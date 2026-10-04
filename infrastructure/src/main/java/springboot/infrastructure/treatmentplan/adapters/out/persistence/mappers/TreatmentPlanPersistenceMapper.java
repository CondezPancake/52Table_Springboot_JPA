package springboot.infrastructure.treatmentplan.adapters.out.persistence.mappers;

import springboot.domain.treatmentplan.model.aggregate.TreatmentPlan;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import springboot.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;

public class TreatmentPlanPersistenceMapper {
    public TreatmentPlanJpaEntity toJpa(TreatmentPlan domain) {
        if (domain == null) { return null; }
        TreatmentPlanJpaEntity jpa = new TreatmentPlanJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEncounterId(domain.encounterId().value());
        jpa.setProfessionalId(domain.professionalId().value());
        jpa.setTitle(domain.title());
        jpa.setDescription(domain.description());
        jpa.setStartDate(domain.startDate());
        jpa.setEndDate(domain.endDate());
        jpa.setTreatmentStatusId(domain.treatmentStatusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public TreatmentPlan toDomain(TreatmentPlanJpaEntity jpa) {
        if (jpa == null) { return null; }
        return TreatmentPlan.restore(
                new TreatmentPlanId(jpa.getId()),
                new EncounterId(jpa.getEncounterId()),
                new ProfessionalId(jpa.getProfessionalId()),
                jpa.getTitle(),
                jpa.getDescription(),
                jpa.getStartDate(),
                jpa.getEndDate(),
                new TreatmentStatusId(jpa.getTreatmentStatusId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
