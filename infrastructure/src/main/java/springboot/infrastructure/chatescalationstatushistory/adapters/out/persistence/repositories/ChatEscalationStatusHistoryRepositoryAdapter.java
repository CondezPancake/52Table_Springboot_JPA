package springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;
import springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;

public class ChatEscalationStatusHistoryRepositoryAdapter implements ChatEscalationStatusHistoryRepository {
    private final ChatEscalationStatusHistoryJpaRepository jpaRepository;
    private final ChatEscalationStatusHistoryPersistenceMapper mapper;
    public ChatEscalationStatusHistoryRepositoryAdapter(ChatEscalationStatusHistoryJpaRepository jpaRepository, ChatEscalationStatusHistoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatEscalationStatusHistory save(ChatEscalationStatusHistory aggregate) {
        ChatEscalationStatusHistoryJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatEscalationStatusHistory> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatEscalationStatusHistory aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
