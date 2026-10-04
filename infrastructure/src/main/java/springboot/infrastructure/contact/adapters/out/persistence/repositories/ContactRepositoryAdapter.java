package springboot.infrastructure.contact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.contact.model.aggregate.Contact;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.contact.port.repository.ContactRepository;
import springboot.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;
import springboot.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;

public class ContactRepositoryAdapter implements ContactRepository {
    private final ContactJpaRepository jpaRepository;
    private final ContactPersistenceMapper mapper;
    public ContactRepositoryAdapter(ContactJpaRepository jpaRepository, ContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Contact save(Contact aggregate) {
        ContactJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Contact> findById(ContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Contact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(Contact aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
