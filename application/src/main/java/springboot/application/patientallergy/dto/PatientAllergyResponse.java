package springboot.application.patientallergy.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record PatientAllergyResponse(
        UUID id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
