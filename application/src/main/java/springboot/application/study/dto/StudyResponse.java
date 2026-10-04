package springboot.application.study.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyResponse(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
