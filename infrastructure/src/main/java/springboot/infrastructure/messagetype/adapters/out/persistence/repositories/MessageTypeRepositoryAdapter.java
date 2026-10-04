package springboot.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.messagetype.model.aggregate.MessageType;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;
import springboot.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;
import springboot.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;

public class MessageTypeRepositoryAdapter implements MessageTypeRepository {
    private final MessageTypeJpaRepository jpaRepository;
    private final MessageTypePersistenceMapper mapper;
    public MessageTypeRepositoryAdapter(MessageTypeJpaRepository jpaRepository, MessageTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public MessageType save(MessageType aggregate) {
        MessageTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<MessageType> findById(MessageTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<MessageType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(MessageType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
