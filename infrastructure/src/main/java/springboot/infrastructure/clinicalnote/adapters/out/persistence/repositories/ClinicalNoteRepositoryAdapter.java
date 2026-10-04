package springboot.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.clinicalnote.model.aggregate.ClinicalNote;
import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import springboot.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;
import springboot.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;

public class ClinicalNoteRepositoryAdapter implements ClinicalNoteRepository {
    private final ClinicalNoteJpaRepository jpaRepository;
    private final ClinicalNotePersistenceMapper mapper;
    public ClinicalNoteRepositoryAdapter(ClinicalNoteJpaRepository jpaRepository, ClinicalNotePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ClinicalNote save(ClinicalNote aggregate) {
        ClinicalNoteJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ClinicalNote> findById(ClinicalNoteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ClinicalNote> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ClinicalNote aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
