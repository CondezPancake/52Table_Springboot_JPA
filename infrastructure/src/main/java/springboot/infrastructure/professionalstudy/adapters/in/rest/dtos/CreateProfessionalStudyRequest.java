package springboot.infrastructure.professionalstudy.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProfessionalStudyRequest(
        @NotNull(message = "studyId is required")
        UUID studyId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        @NotNull(message = "title is required")
        @Size(max = 100, message = "title must have at most 100 characters")
        String title,

        @NotNull(message = "university is required")
        @Size(max = 100, message = "university must have at most 100 characters")
        String university,

        @NotNull(message = "valid is required")
        Boolean valid,

        @Size(max = 60, message = "resolutionNumber must have at most 60 characters")
        String resolutionNumber,

        @NotNull(message = "countryId is required")
        UUID countryId
) {
}
