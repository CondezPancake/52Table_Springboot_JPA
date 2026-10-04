package springboot.application.chatconversation.usecase;

import java.time.LocalDateTime;

import springboot.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import springboot.domain.chatconversation.event.ChatConversationDeletedEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;

public class DeleteChatConversationUseCase {
    private final ChatConversationRepository repository;
    public DeleteChatConversationUseCase(ChatConversationRepository repository) { this.repository = repository; }

    public ChatConversationDeletedEvent execute(ChatConversationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatConversationNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatConversationDeletedEvent(id, LocalDateTime.now());
    }
}
