package springboot.infrastructure.diagnosticsystem.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import springboot.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;
import springboot.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;

public class DiagnosticSystemRepositoryAdapter implements DiagnosticSystemRepository {
    private final DiagnosticSystemJpaRepository jpaRepository;
    private final DiagnosticSystemPersistenceMapper mapper;
    public DiagnosticSystemRepositoryAdapter(DiagnosticSystemJpaRepository jpaRepository, DiagnosticSystemPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public DiagnosticSystem save(DiagnosticSystem aggregate) {
        DiagnosticSystemJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<DiagnosticSystem> findById(DiagnosticSystemId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<DiagnosticSystem> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(DiagnosticSystem aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
