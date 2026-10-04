package springboot.infrastructure.encounterstatus.adapters.out.persistence.mappers;

import springboot.domain.encounterstatus.model.aggregate.EncounterStatus;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;

import springboot.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;

public class EncounterStatusPersistenceMapper {
    public EncounterStatusJpaEntity toJpa(EncounterStatus domain) {
        if (domain == null) { return null; }
        EncounterStatusJpaEntity jpa = new EncounterStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public EncounterStatus toDomain(EncounterStatusJpaEntity jpa) {
        if (jpa == null) { return null; }
        return EncounterStatus.restore(
                new EncounterStatusId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.isActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
