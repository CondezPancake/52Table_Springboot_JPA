package springboot.domain.chatmessage.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatmessage.model.aggregate.ChatMessage;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage aggregate);
    Optional<ChatMessage> findById(ChatMessageId id);
    List<ChatMessage> findAll();

    void delete(ChatMessage aggregate);
}
