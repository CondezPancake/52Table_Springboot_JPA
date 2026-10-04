package springboot.infrastructure.study.adapters.out.persistence.mappers;

import springboot.domain.study.model.aggregate.Study;
import springboot.domain.study.model.valueobject.StudyId;

import springboot.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;

public class StudyPersistenceMapper {
    public StudyJpaEntity toJpa(Study domain) {
        if (domain == null) { return null; }
        StudyJpaEntity jpa = new StudyJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setName(domain.name());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Study toDomain(StudyJpaEntity jpa) {
        if (jpa == null) { return null; }
        return Study.restore(
                new StudyId(jpa.getId()),
                jpa.getName(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
