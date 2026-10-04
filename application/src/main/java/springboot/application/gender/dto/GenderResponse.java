package springboot.application.gender.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GenderResponse(
        UUID id,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
