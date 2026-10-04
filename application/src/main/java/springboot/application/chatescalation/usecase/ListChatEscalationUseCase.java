package springboot.application.chatescalation.usecase;

import java.util.List;

import springboot.application.chatescalation.dto.ChatEscalationResponse;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;

public class ListChatEscalationUseCase {
    private final ChatEscalationRepository repository;
    public ListChatEscalationUseCase(ChatEscalationRepository repository) { this.repository = repository; }

    public List<ChatEscalationResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ChatEscalationResponse(
                                aggregate.id().value(),
                                aggregate.conversationId().value(),
                                aggregate.statusId().value(),
                                aggregate.fromAi(),
                                aggregate.reason(),
                                aggregate.createdAt()))
                .toList();
    }
}
