package springboot.infrastructure.escalationstatus.adapters.out.persistence.mappers;

import springboot.domain.escalationstatus.model.aggregate.EscalationStatus;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

import springboot.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;

public class EscalationStatusPersistenceMapper {
    public EscalationStatusJpaEntity toJpa(EscalationStatus domain) {
        if (domain == null) { return null; }
        EscalationStatusJpaEntity jpa = new EscalationStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameStatus(domain.nameStatus());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public EscalationStatus toDomain(EscalationStatusJpaEntity jpa) {
        if (jpa == null) { return null; }
        return EscalationStatus.restore(
                new EscalationStatusId(jpa.getId()),
                jpa.getNameStatus(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
