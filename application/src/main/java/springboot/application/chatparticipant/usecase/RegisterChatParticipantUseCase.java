package springboot.application.chatparticipant.usecase;

import springboot.application.chatparticipant.command.RegisterChatParticipantCommand;
import springboot.application.chatparticipant.dto.ChatParticipantResponse;
import springboot.domain.chatparticipant.model.aggregate.ChatParticipant;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class RegisterChatParticipantUseCase {
    private final ChatParticipantRepository repository;
    public RegisterChatParticipantUseCase(ChatParticipantRepository repository) { this.repository = repository; }

    public ChatParticipantResponse execute(RegisterChatParticipantCommand command) {
        ChatParticipant aggregate = ChatParticipant.register(
                command.conversationId(),
                command.participantTypeId(),
                command.patientId(),
                command.professionalId());
        ChatParticipant saved = repository.save(aggregate);
        return new ChatParticipantResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.participantTypeId().value(),
                saved.patientId() == null ? null : saved.patientId().value(),
                saved.professionalId() == null ? null : saved.professionalId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
