package springboot.infrastructure.chatparticipant.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateChatParticipantRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "participantTypeId is required")
        UUID participantTypeId,

        UUID patientId,

        UUID professionalId
) {
}
