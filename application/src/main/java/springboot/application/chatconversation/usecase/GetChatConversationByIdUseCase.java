package springboot.application.chatconversation.usecase;

import springboot.application.chatconversation.dto.ChatConversationResponse;
import springboot.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;

public class GetChatConversationByIdUseCase {
    private final ChatConversationRepository repository;
    public GetChatConversationByIdUseCase(ChatConversationRepository repository) { this.repository = repository; }

    public ChatConversationResponse execute(ChatConversationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatConversationNotFoundApplicationException(id.value().toString()));
        return new ChatConversationResponse(
                aggregate.id().value(),
                aggregate.conversationStatusId().value(),
                aggregate.priorityId().value(),
                aggregate.lastMessageAt(),
                aggregate.closed(),
                aggregate.closedAt(),
                aggregate.closedBy(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
