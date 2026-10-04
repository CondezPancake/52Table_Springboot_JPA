package springboot.application.priority.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record PriorityResponse(
        UUID id,
        String namePriority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
