package springboot.application.chatmessage.usecase;

import springboot.application.chatmessage.command.UpdateChatMessageCommand;
import springboot.application.chatmessage.dto.ChatMessageResponse;
import springboot.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;

public class UpdateChatMessageUseCase {
    private final ChatMessageRepository repository;
    public UpdateChatMessageUseCase(ChatMessageRepository repository) { this.repository = repository; }

    public ChatMessageResponse execute(UpdateChatMessageCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatMessageNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.messageTypeId(),
                command.participantId(),
                command.content(),
                command.metadata());
        var saved = repository.save(aggregate);
        return new ChatMessageResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.messageTypeId().value(),
                saved.participantId().value(),
                saved.content(),
                saved.metadata(),
                saved.createdAt());
    }
}
