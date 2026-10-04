package springboot.application.sendertype.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record SenderTypeResponse(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
