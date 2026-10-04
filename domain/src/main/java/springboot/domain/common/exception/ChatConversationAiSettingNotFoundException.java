package springboot.domain.common.exception;

import springboot.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public class ChatConversationAiSettingNotFoundException extends RuntimeException {
    public ChatConversationAiSettingNotFoundException(ChatConversationAiSettingId id) {
        super("ChatConversationAiSetting not found with id: " + id.value());
    }
}
