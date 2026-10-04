package springboot.application.professionaltype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfessionalTypeResponse(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
