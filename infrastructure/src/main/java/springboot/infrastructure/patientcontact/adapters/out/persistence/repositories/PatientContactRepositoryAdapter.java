package springboot.infrastructure.patientcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.patientcontact.model.aggregate.PatientContact;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.patientcontact.port.repository.PatientContactRepository;
import springboot.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;
import springboot.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;

public class PatientContactRepositoryAdapter implements PatientContactRepository {
    private final PatientContactJpaRepository jpaRepository;
    private final PatientContactPersistenceMapper mapper;
    public PatientContactRepositoryAdapter(PatientContactJpaRepository jpaRepository, PatientContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public PatientContact save(PatientContact aggregate) {
        PatientContactJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<PatientContact> findById(PatientContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<PatientContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(PatientContact aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
