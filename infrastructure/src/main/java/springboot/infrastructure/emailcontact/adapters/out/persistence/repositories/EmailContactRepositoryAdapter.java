package springboot.infrastructure.emailcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.emailcontact.model.aggregate.EmailContact;
import springboot.domain.emailcontact.model.valueobject.EmailContactId;
import springboot.domain.emailcontact.port.repository.EmailContactRepository;
import springboot.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;
import springboot.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;

public class EmailContactRepositoryAdapter implements EmailContactRepository {
    private final EmailContactJpaRepository jpaRepository;
    private final EmailContactPersistenceMapper mapper;
    public EmailContactRepositoryAdapter(EmailContactJpaRepository jpaRepository, EmailContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public EmailContact save(EmailContact aggregate) {
        EmailContactJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<EmailContact> findById(EmailContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<EmailContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(EmailContact aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
