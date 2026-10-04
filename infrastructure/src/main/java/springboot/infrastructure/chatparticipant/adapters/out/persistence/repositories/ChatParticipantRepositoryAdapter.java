package springboot.infrastructure.chatparticipant.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatparticipant.model.aggregate.ChatParticipant;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.mappers.ChatParticipantPersistenceMapper;

public class ChatParticipantRepositoryAdapter implements ChatParticipantRepository {
    private final ChatParticipantJpaRepository jpaRepository;
    private final ChatParticipantPersistenceMapper mapper;
    public ChatParticipantRepositoryAdapter(ChatParticipantJpaRepository jpaRepository, ChatParticipantPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatParticipant save(ChatParticipant aggregate) {
        ChatParticipantJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatParticipant> findById(ChatParticipantId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatParticipant> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatParticipant aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
