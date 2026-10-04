package springboot.application.chatairun.usecase;

import java.time.LocalDateTime;

import springboot.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import springboot.domain.chatairun.event.ChatAiRunDeletedEvent;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;

public class DeleteChatAiRunUseCase {
    private final ChatAiRunRepository repository;
    public DeleteChatAiRunUseCase(ChatAiRunRepository repository) { this.repository = repository; }

    public ChatAiRunDeletedEvent execute(ChatAiRunId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatAiRunDeletedEvent(id, LocalDateTime.now());
    }
}
