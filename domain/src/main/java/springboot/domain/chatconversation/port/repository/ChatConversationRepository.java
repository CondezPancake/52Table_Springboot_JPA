package springboot.domain.chatconversation.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatconversation.model.aggregate.ChatConversation;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public interface ChatConversationRepository {
    ChatConversation save(ChatConversation aggregate);
    Optional<ChatConversation> findById(ChatConversationId id);
    List<ChatConversation> findAll();

    void delete(ChatConversation aggregate);
}
