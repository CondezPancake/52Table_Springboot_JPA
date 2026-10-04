package springboot.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.medicationroute.model.aggregate.MedicationRoute;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;
import springboot.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;
import springboot.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;

public class MedicationRouteRepositoryAdapter implements MedicationRouteRepository {
    private final MedicationRouteJpaRepository jpaRepository;
    private final MedicationRoutePersistenceMapper mapper;
    public MedicationRouteRepositoryAdapter(MedicationRouteJpaRepository jpaRepository, MedicationRoutePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public MedicationRoute save(MedicationRoute aggregate) {
        MedicationRouteJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<MedicationRoute> findById(MedicationRouteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<MedicationRoute> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(MedicationRoute aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
