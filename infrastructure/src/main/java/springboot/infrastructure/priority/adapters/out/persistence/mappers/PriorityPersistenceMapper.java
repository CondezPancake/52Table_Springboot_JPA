package springboot.infrastructure.priority.adapters.out.persistence.mappers;

import springboot.domain.priority.model.aggregate.Priority;
import springboot.domain.priority.model.valueobject.PriorityId;

import springboot.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;

public class PriorityPersistenceMapper {
    public PriorityJpaEntity toJpa(Priority domain) {
        if (domain == null) { return null; }
        PriorityJpaEntity jpa = new PriorityJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNamePriority(domain.namePriority());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Priority toDomain(PriorityJpaEntity jpa) {
        if (jpa == null) { return null; }
        return Priority.restore(
                new PriorityId(jpa.getId()),
                jpa.getNamePriority(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
