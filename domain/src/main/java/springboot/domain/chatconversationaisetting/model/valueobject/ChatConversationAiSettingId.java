package springboot.domain.chatconversationaisetting.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ChatConversationAiSettingId(UUID value) {
    public ChatConversationAiSettingId {
        Objects.requireNonNull(value, "ChatConversationAiSettingId value must not be null");
    }

    public static ChatConversationAiSettingId generate() {
        return new ChatConversationAiSettingId(UUID.randomUUID());
    }
}
