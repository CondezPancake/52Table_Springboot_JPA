package springboot.infrastructure.treatmentgoal.adapters.out.persistence.mappers;

import springboot.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;

public class TreatmentGoalPersistenceMapper {
    public TreatmentGoalJpaEntity toJpa(TreatmentGoal domain) {
        if (domain == null) { return null; }
        TreatmentGoalJpaEntity jpa = new TreatmentGoalJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setTreatmentPlanId(domain.treatmentPlanId().value());
        jpa.setDescription(domain.description());
        jpa.setTargetDate(domain.targetDate());
        jpa.setCompletedAt(domain.completedAt());
        jpa.setNotes(domain.notes());
        jpa.setTreatmentGoalStatusId(domain.treatmentGoalStatusId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public TreatmentGoal toDomain(TreatmentGoalJpaEntity jpa) {
        if (jpa == null) { return null; }
        return TreatmentGoal.restore(
                new TreatmentGoalId(jpa.getId()),
                new TreatmentPlanId(jpa.getTreatmentPlanId()),
                jpa.getDescription(),
                jpa.getTargetDate(),
                jpa.getCompletedAt(),
                jpa.getNotes(),
                new TreatmentGoalStatusId(jpa.getTreatmentGoalStatusId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
