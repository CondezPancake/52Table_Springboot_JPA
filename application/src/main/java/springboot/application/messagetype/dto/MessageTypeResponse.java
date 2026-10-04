package springboot.application.messagetype.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record MessageTypeResponse(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
