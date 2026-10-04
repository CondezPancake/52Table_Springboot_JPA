package springboot.application.chatescalationstatushistory.usecase;

import java.util.List;

import springboot.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class ListChatEscalationStatusHistoryUseCase {
    private final ChatEscalationStatusHistoryRepository repository;
    public ListChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) { this.repository = repository; }

    public List<ChatEscalationStatusHistoryResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatEscalationStatusHistoryResponse(
                                aggregate.id().value(),
                                aggregate.escalationId().value(),
                                aggregate.escalationStatusId().value(),
                                aggregate.changedAt(),
                                aggregate.createdAt()))
                .toList();
    }
}
