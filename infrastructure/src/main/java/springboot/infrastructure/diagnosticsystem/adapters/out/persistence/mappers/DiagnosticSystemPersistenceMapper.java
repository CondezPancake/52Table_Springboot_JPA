package springboot.infrastructure.diagnosticsystem.adapters.out.persistence.mappers;

import springboot.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

import springboot.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;

public class DiagnosticSystemPersistenceMapper {
    public DiagnosticSystemJpaEntity toJpa(DiagnosticSystem domain) {
        if (domain == null) { return null; }
        DiagnosticSystemJpaEntity jpa = new DiagnosticSystemJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setVersion(domain.version());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public DiagnosticSystem toDomain(DiagnosticSystemJpaEntity jpa) {
        if (jpa == null) { return null; }
        return DiagnosticSystem.restore(
                new DiagnosticSystemId(jpa.getId()),
                jpa.getCode(),
                jpa.getName(),
                jpa.isActive(),
                jpa.getVersion(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
