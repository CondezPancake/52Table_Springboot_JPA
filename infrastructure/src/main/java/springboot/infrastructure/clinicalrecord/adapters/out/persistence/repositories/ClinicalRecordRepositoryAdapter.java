package springboot.infrastructure.clinicalrecord.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.mappers.ClinicalRecordPersistenceMapper;

public class ClinicalRecordRepositoryAdapter implements ClinicalRecordRepository {
    private final ClinicalRecordJpaRepository jpaRepository;
    private final ClinicalRecordPersistenceMapper mapper;
    public ClinicalRecordRepositoryAdapter(ClinicalRecordJpaRepository jpaRepository, ClinicalRecordPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ClinicalRecord save(ClinicalRecord aggregate) {
        ClinicalRecordJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ClinicalRecord> findById(ClinicalRecordId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ClinicalRecord> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ClinicalRecord aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
