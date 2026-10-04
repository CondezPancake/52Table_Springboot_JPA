package springboot.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.patient.model.aggregate.Patient;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.patient.port.repository.PatientRepository;
import springboot.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;
import springboot.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;

public class PatientRepositoryAdapter implements PatientRepository {
    private final PatientJpaRepository jpaRepository;
    private final PatientPersistenceMapper mapper;
    public PatientRepositoryAdapter(PatientJpaRepository jpaRepository, PatientPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Patient save(Patient aggregate) {
        PatientJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Patient> findById(PatientId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Patient> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Patient aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
