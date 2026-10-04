package springboot.infrastructure.chatescalationassignment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import springboot.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;
import springboot.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;

public class ChatEscalationAssignmentRepositoryAdapter implements ChatEscalationAssignmentRepository {
    private final ChatEscalationAssignmentJpaRepository jpaRepository;
    private final ChatEscalationAssignmentPersistenceMapper mapper;
    public ChatEscalationAssignmentRepositoryAdapter(ChatEscalationAssignmentJpaRepository jpaRepository, ChatEscalationAssignmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ChatEscalationAssignment save(ChatEscalationAssignment aggregate) {
        ChatEscalationAssignmentJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ChatEscalationAssignment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ChatEscalationAssignment aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
