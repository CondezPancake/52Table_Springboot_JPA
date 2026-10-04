package springboot.infrastructure.mentalstatusexam.adapters.in.rest.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateMentalStatusExamRequest(
        @NotNull(message = "encounterId is required")
        UUID encounterId,

        @NotNull(message = "appearance is required")
        String appearance,

        @NotNull(message = "behavior is required")
        String behavior,

        @NotNull(message = "attitude is required")
        String attitude,

        @NotNull(message = "consciousness is required")
        String consciousness,

        @NotNull(message = "orientation is required")
        String orientation,

        @NotNull(message = "attention is required")
        String attention,

        @NotNull(message = "memory is required")
        String memory,

        @NotNull(message = "speech is required")
        String speech,

        @NotNull(message = "mood is required")
        String mood,

        @NotNull(message = "affect is required")
        String affect,

        @NotNull(message = "thoughtProcess is required")
        String thoughtProcess,

        @NotNull(message = "thoughtContent is required")
        String thoughtContent,

        @NotNull(message = "perception is required")
        String perception,

        @NotNull(message = "judgment is required")
        String judgment,

        @NotNull(message = "insight is required")
        String insight,

        @NotNull(message = "psychomotorActivity is required")
        String psychomotorActivity,

        @NotNull(message = "observations is required")
        String observations,

        @NotNull(message = "createdBy is required")
        UUID createdBy
) {
}
