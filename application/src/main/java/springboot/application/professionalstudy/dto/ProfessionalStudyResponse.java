package springboot.application.professionalstudy.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ProfessionalStudyResponse(
        UUID id,
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        boolean valid,
        String resolutionNumber,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
