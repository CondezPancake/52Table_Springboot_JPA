package springboot.application.chatmessage.usecase;

import springboot.application.chatmessage.dto.ChatMessageResponse;
import springboot.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;

public class GetChatMessageByIdUseCase {
    private final ChatMessageRepository repository;
    public GetChatMessageByIdUseCase(ChatMessageRepository repository) { this.repository = repository; }

    public ChatMessageResponse execute(ChatMessageId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatMessageNotFoundApplicationException(id.value().toString()));
        return new ChatMessageResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.messageTypeId().value(),
                aggregate.participantId().value(),
                aggregate.content(),
                aggregate.metadata(),
                aggregate.createdAt());
    }
}
