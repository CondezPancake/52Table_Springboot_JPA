package springboot.application.mentalstatusexam.command;

import java.util.Objects;


import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record UpdateMentalStatusExamCommand(
        MentalStatusExamId id,
        EncounterId encounterId,
        String appearance,
        String behavior,
        String attitude,
        String consciousness,
        String orientation,
        String attention,
        String memory,
        String speech,
        String mood,
        String affect,
        String thoughtProcess,
        String thoughtContent,
        String perception,
        String judgment,
        String insight,
        String psychomotorActivity,
        String observations,
        ProfessionalId createdBy
) {
    public UpdateMentalStatusExamCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(appearance, "appearance must not be null");
        Objects.requireNonNull(behavior, "behavior must not be null");
        Objects.requireNonNull(attitude, "attitude must not be null");
        Objects.requireNonNull(consciousness, "consciousness must not be null");
        Objects.requireNonNull(orientation, "orientation must not be null");
        Objects.requireNonNull(attention, "attention must not be null");
        Objects.requireNonNull(memory, "memory must not be null");
        Objects.requireNonNull(speech, "speech must not be null");
        Objects.requireNonNull(mood, "mood must not be null");
        Objects.requireNonNull(affect, "affect must not be null");
        Objects.requireNonNull(thoughtProcess, "thoughtProcess must not be null");
        Objects.requireNonNull(thoughtContent, "thoughtContent must not be null");
        Objects.requireNonNull(perception, "perception must not be null");
        Objects.requireNonNull(judgment, "judgment must not be null");
        Objects.requireNonNull(insight, "insight must not be null");
        Objects.requireNonNull(psychomotorActivity, "psychomotorActivity must not be null");
        Objects.requireNonNull(observations, "observations must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
    }
}
