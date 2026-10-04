package springboot.domain.chatescalation.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatescalation.model.aggregate.ChatEscalation;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;

public interface ChatEscalationRepository {
    ChatEscalation save(ChatEscalation aggregate);
    Optional<ChatEscalation> findById(ChatEscalationId id);
    List<ChatEscalation> findAll();

    void delete(ChatEscalation aggregate);
}
