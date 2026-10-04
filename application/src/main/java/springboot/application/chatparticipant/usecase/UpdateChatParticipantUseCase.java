package springboot.application.chatparticipant.usecase;

import springboot.application.chatparticipant.command.UpdateChatParticipantCommand;
import springboot.application.chatparticipant.dto.ChatParticipantResponse;
import springboot.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class UpdateChatParticipantUseCase {
    private final ChatParticipantRepository repository;
    public UpdateChatParticipantUseCase(ChatParticipantRepository repository) { this.repository = repository; }

    public ChatParticipantResponse execute(UpdateChatParticipantCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatParticipantNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.participantTypeId(),
                command.patientId(),
                command.professionalId());
        var saved = repository.save(aggregate);
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
