package springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;
import springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;

public class ClinicalRecordStatusRepositoryAdapter implements ClinicalRecordStatusRepository {
    private final ClinicalRecordStatusJpaRepository jpaRepository;
    private final ClinicalRecordStatusPersistenceMapper mapper;
    public ClinicalRecordStatusRepositoryAdapter(ClinicalRecordStatusJpaRepository jpaRepository, ClinicalRecordStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ClinicalRecordStatus save(ClinicalRecordStatus aggregate) {
        ClinicalRecordStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ClinicalRecordStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(ClinicalRecordStatus aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
