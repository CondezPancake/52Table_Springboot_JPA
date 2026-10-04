package springboot.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatescalation.model.aggregate.ChatEscalation;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;
import springboot.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;
import springboot.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;

public class ChatEscalationRepositoryAdapter implements ChatEscalationRepository {
    private final ChatEscalationJpaRepository jpaRepository;
    private final ChatEscalationPersistenceMapper mapper;
    public ChatEscalationRepositoryAdapter(ChatEscalationJpaRepository jpaRepository, ChatEscalationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatEscalation save(ChatEscalation aggregate) {
        ChatEscalationJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatEscalation> findById(ChatEscalationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatEscalation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatEscalation aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
