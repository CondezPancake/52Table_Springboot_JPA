package springboot.application.conversationstatus.usecase;

import java.util.List;

import springboot.application.conversationstatus.dto.ConversationStatusResponse;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class ListConversationStatusUseCase {
    private final ConversationStatusRepository repository;
    public ListConversationStatusUseCase(ConversationStatusRepository repository) { this.repository = repository; }

    public List<ConversationStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ConversationStatusResponse(
                                aggregate.id().value(),
                                aggregate.nameStatus(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
