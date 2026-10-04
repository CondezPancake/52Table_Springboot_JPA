package springboot.application.professional.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ProfessionalResponse(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalTypeId,
        String licenseNumber,
        boolean active,
        UUID cityId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
