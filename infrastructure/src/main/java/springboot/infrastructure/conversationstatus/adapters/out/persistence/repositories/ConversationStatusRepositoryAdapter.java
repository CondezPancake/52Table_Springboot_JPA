package springboot.infrastructure.conversationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.conversationstatus.model.aggregate.ConversationStatus;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;
import springboot.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;
import springboot.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;

public class ConversationStatusRepositoryAdapter implements ConversationStatusRepository {
    private final ConversationStatusJpaRepository jpaRepository;
    private final ConversationStatusPersistenceMapper mapper;
    public ConversationStatusRepositoryAdapter(ConversationStatusJpaRepository jpaRepository, ConversationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ConversationStatus save(ConversationStatus aggregate) {
        ConversationStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ConversationStatus> findById(ConversationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ConversationStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ConversationStatus aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
