package springboot.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.patientallergy.model.aggregate.PatientAllergy;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;
import springboot.domain.patientallergy.port.repository.PatientAllergyRepository;
import springboot.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;
import springboot.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;

public class PatientAllergyRepositoryAdapter implements PatientAllergyRepository {
    private final PatientAllergyJpaRepository jpaRepository;
    private final PatientAllergyPersistenceMapper mapper;
    public PatientAllergyRepositoryAdapter(PatientAllergyJpaRepository jpaRepository, PatientAllergyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public PatientAllergy save(PatientAllergy aggregate) {
        PatientAllergyJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<PatientAllergy> findById(PatientAllergyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<PatientAllergy> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(PatientAllergy aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
