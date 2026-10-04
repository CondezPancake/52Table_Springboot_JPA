package springboot.application.chatairunerror.usecase;

import java.time.LocalDateTime;

import springboot.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import springboot.domain.chatairunerror.event.ChatAiRunErrorDeletedEvent;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class DeleteChatAiRunErrorUseCase {
    private final ChatAiRunErrorRepository repository;
    public DeleteChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) { this.repository = repository; }

    public ChatAiRunErrorDeletedEvent execute(ChatAiRunErrorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatAiRunErrorDeletedEvent(id, LocalDateTime.now());
    }
}
