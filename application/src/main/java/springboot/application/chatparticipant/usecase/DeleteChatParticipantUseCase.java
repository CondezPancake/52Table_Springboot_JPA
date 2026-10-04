package springboot.application.chatparticipant.usecase;

import java.time.LocalDateTime;

import springboot.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import springboot.domain.chatparticipant.event.ChatParticipantDeletedEvent;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class DeleteChatParticipantUseCase {
    private final ChatParticipantRepository repository;
    public DeleteChatParticipantUseCase(ChatParticipantRepository repository) { this.repository = repository; }

    public ChatParticipantDeletedEvent execute(ChatParticipantId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatParticipantNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatParticipantDeletedEvent(id, LocalDateTime.now());
    }
}
