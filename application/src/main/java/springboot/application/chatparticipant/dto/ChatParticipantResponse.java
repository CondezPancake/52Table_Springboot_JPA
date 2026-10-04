package springboot.application.chatparticipant.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatParticipantResponse(
        UUID id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
