package springboot.domain.messagetype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.messagetype.model.aggregate.MessageType;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

public interface MessageTypeRepository {
    MessageType save(MessageType aggregate);
    Optional<MessageType> findById(MessageTypeId id);
    List<MessageType> findAll();

    void delete(MessageType aggregate);
}
