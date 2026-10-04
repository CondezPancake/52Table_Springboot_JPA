package springboot.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.phonecontact.model.aggregate.PhoneContact;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;
import springboot.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;
import springboot.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;

public class PhoneContactRepositoryAdapter implements PhoneContactRepository {
    private final PhoneContactJpaRepository jpaRepository;
    private final PhoneContactPersistenceMapper mapper;
    public PhoneContactRepositoryAdapter(PhoneContactJpaRepository jpaRepository, PhoneContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public PhoneContact save(PhoneContact aggregate) {
        PhoneContactJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<PhoneContact> findById(PhoneContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<PhoneContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(PhoneContact aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
