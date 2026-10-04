package springboot.application.documenttype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentTypeResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
