package springboot.domain.chatairunerror.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatairunerror.event.ChatAiRunErrorRegisteredEvent;
import springboot.domain.chatairunerror.event.ChatAiRunErrorUpdatedEvent;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunError extends AggregateRoot {
    private final ChatAiRunErrorId id;
    private ChatAiRunId aiRunId;
    private String errorMessage;
    private String errorCode;
    private String providerErrorId;
    private final LocalDateTime createdAt;

    private ChatAiRunError(
            ChatAiRunErrorId id,
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.aiRunId = Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        this.errorMessage = Objects.requireNonNull(errorMessage, "errorMessage must not be null");
        this.errorCode = Objects.requireNonNull(errorCode, "errorCode must not be null");
        this.providerErrorId = Objects.requireNonNull(providerErrorId, "providerErrorId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ChatAiRunError register(
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId) {
        ChatAiRunErrorId id = ChatAiRunErrorId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatAiRunError aggregate = new ChatAiRunError(
                id,
                aiRunId,
                errorMessage,
                errorCode,
                providerErrorId,
                now);
        aggregate.recordEvent(new ChatAiRunErrorRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatAiRunError restore(
            ChatAiRunErrorId id,
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        return new ChatAiRunError(
                id,
                aiRunId,
                errorMessage,
                errorCode,
                providerErrorId,
                createdAt);
    }

    public void update(
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId) {
        this.aiRunId = Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        this.errorMessage = Objects.requireNonNull(errorMessage, "errorMessage must not be null");
        this.errorCode = Objects.requireNonNull(errorCode, "errorCode must not be null");
        this.providerErrorId = Objects.requireNonNull(providerErrorId, "providerErrorId must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ChatAiRunErrorUpdatedEvent(
                        this.id,
                        this.aiRunId,
                        this.errorMessage,
                        this.errorCode,
                        this.providerErrorId,
                        occurredOn));
    }

    public ChatAiRunErrorId id() {
        return id;
    }

    public ChatAiRunId aiRunId() {
        return aiRunId;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public String errorCode() {
        return errorCode;
    }

    public String providerErrorId() {
        return providerErrorId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
