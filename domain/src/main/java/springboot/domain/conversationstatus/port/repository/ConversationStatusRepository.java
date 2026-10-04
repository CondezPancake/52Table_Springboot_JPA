package springboot.domain.conversationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.conversationstatus.model.aggregate.ConversationStatus;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;

public interface ConversationStatusRepository {
    ConversationStatus save(ConversationStatus aggregate);
    Optional<ConversationStatus> findById(ConversationStatusId id);
    List<ConversationStatus> findAll();

    void delete(ConversationStatus aggregate);
}
