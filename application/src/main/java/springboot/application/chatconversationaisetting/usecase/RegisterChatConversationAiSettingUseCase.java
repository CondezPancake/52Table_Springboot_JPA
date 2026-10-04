package springboot.application.chatconversationaisetting.usecase;

import springboot.application.chatconversationaisetting.command.RegisterChatConversationAiSettingCommand;
import springboot.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import springboot.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class RegisterChatConversationAiSettingUseCase {
    private final ChatConversationAiSettingRepository repository;
    public RegisterChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) { this.repository = repository; }

    public ChatConversationAiSettingResponse execute(RegisterChatConversationAiSettingCommand command) {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(
                command.conversationId(),
                command.aiEnabled(),
                command.defaultModelId());
        ChatConversationAiSetting saved = repository.save(aggregate);
        return new ChatConversationAiSettingResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.aiEnabled(),
                saved.defaultModelId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
