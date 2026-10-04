package springboot.application.chatconversationaisetting.usecase;

import springboot.application.chatconversationaisetting.command.UpdateChatConversationAiSettingCommand;
import springboot.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import springboot.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class UpdateChatConversationAiSettingUseCase {
    private final ChatConversationAiSettingRepository repository;
    public UpdateChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) { this.repository = repository; }

    public ChatConversationAiSettingResponse execute(UpdateChatConversationAiSettingCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.aiEnabled(),
                command.defaultModelId());
        var saved = repository.save(aggregate);
        return new ChatConversationAiSettingResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.aiEnabled(),
                saved.defaultModelId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
