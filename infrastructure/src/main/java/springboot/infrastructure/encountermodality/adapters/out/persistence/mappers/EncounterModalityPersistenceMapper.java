package springboot.infrastructure.encountermodality.adapters.out.persistence.mappers;

import springboot.domain.encountermodality.model.aggregate.EncounterModality;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;

import springboot.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;

public class EncounterModalityPersistenceMapper {
    public EncounterModalityJpaEntity toJpa(EncounterModality domain) {
        if (domain == null) { return null; }
        EncounterModalityJpaEntity jpa = new EncounterModalityJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public EncounterModality toDomain(EncounterModalityJpaEntity jpa) {
        if (jpa == null) { return null; }
        return EncounterModality.restore(
                new EncounterModalityId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.isActive(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
