package springboot.domain.chatescalationstatushistory.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public interface ChatEscalationStatusHistoryRepository {
    ChatEscalationStatusHistory save(ChatEscalationStatusHistory aggregate);
    Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id);
    List<ChatEscalationStatusHistory> findAll();

    void delete(ChatEscalationStatusHistory aggregate);
}
