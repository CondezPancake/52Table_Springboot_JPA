package springboot.application.chatescalation.usecase;

import springboot.application.chatescalation.dto.ChatEscalationResponse;
import springboot.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;

public class GetChatEscalationByIdUseCase {
    private final ChatEscalationRepository repository;
    public GetChatEscalationByIdUseCase(ChatEscalationRepository repository) { this.repository = repository; }

    public ChatEscalationResponse execute(ChatEscalationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationNotFoundApplicationException(id.value().toString()));
        return new ChatEscalationResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.statusId().value(),
                aggregate.fromAi(),
                aggregate.reason(),
                aggregate.createdAt());
    }
}
