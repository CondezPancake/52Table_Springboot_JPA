package springboot.application.chatmessage.usecase;

import java.time.LocalDateTime;

import springboot.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import springboot.domain.chatmessage.event.ChatMessageDeletedEvent;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;

public class DeleteChatMessageUseCase {
    private final ChatMessageRepository repository;
    public DeleteChatMessageUseCase(ChatMessageRepository repository) { this.repository = repository; }

    public ChatMessageDeletedEvent execute(ChatMessageId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatMessageNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatMessageDeletedEvent(id, LocalDateTime.now());
    }
}
