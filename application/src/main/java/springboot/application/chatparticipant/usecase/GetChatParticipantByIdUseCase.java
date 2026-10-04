package springboot.application.chatparticipant.usecase;

import springboot.application.chatparticipant.dto.ChatParticipantResponse;
import springboot.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class GetChatParticipantByIdUseCase {
    private final ChatParticipantRepository repository;
    public GetChatParticipantByIdUseCase(ChatParticipantRepository repository) { this.repository = repository; }

    public ChatParticipantResponse execute(ChatParticipantId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatParticipantNotFoundApplicationException(id.value().toString()));
        return new ChatParticipantResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.participantTypeId().value(),
                aggregate.patientId() == null ? null : aggregate.patientId().value(),
                aggregate.professionalId() == null ? null : aggregate.professionalId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
