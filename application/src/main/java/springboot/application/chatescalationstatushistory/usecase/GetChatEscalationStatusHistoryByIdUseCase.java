package springboot.application.chatescalationstatushistory.usecase;

import springboot.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import springboot.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class GetChatEscalationStatusHistoryByIdUseCase {
    private final ChatEscalationStatusHistoryRepository repository;
    public GetChatEscalationStatusHistoryByIdUseCase(ChatEscalationStatusHistoryRepository repository) { this.repository = repository; }

    public ChatEscalationStatusHistoryResponse execute(ChatEscalationStatusHistoryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundApplicationException(id.value().toString()));
        return new ChatEscalationStatusHistoryResponse(
                aggregate.id().value(),
                aggregate.escalationId().value(),
                aggregate.escalationStatusId().value(),
                aggregate.changedAt(),
                aggregate.createdAt());
    }
}
