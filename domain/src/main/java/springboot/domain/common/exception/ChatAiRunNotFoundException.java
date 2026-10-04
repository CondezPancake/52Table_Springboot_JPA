package springboot.domain.common.exception;

import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunNotFoundException extends RuntimeException {
    public ChatAiRunNotFoundException(ChatAiRunId id) {
        super("ChatAiRun not found with id: " + id.value());
    }
}
