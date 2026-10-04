package springboot.domain.mentalstatusexam.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.mentalstatusexam.event.MentalStatusExamRegisteredEvent;
import springboot.domain.mentalstatusexam.event.MentalStatusExamUpdatedEvent;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class MentalStatusExam extends AggregateRoot {
    private final MentalStatusExamId id;
    private EncounterId encounterId;
    private String appearance;
    private String behavior;
    private String attitude;
    private String consciousness;
    private String orientation;
    private String attention;
    private String memory;
    private String speech;
    private String mood;
    private String affect;
    private String thoughtProcess;
    private String thoughtContent;
    private String perception;
    private String judgment;
    private String insight;
    private String psychomotorActivity;
    private String observations;
    private ProfessionalId createdBy;
    private final LocalDateTime createdAt;

    private MentalStatusExam(
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
            ProfessionalId createdBy,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.appearance = Objects.requireNonNull(appearance, "appearance must not be null");
        this.behavior = Objects.requireNonNull(behavior, "behavior must not be null");
        this.attitude = Objects.requireNonNull(attitude, "attitude must not be null");
        this.consciousness = Objects.requireNonNull(consciousness, "consciousness must not be null");
        this.orientation = Objects.requireNonNull(orientation, "orientation must not be null");
        this.attention = Objects.requireNonNull(attention, "attention must not be null");
        this.memory = Objects.requireNonNull(memory, "memory must not be null");
        this.speech = Objects.requireNonNull(speech, "speech must not be null");
        this.mood = Objects.requireNonNull(mood, "mood must not be null");
        this.affect = Objects.requireNonNull(affect, "affect must not be null");
        this.thoughtProcess = Objects.requireNonNull(thoughtProcess, "thoughtProcess must not be null");
        this.thoughtContent = Objects.requireNonNull(thoughtContent, "thoughtContent must not be null");
        this.perception = Objects.requireNonNull(perception, "perception must not be null");
        this.judgment = Objects.requireNonNull(judgment, "judgment must not be null");
        this.insight = Objects.requireNonNull(insight, "insight must not be null");
        this.psychomotorActivity = Objects.requireNonNull(psychomotorActivity, "psychomotorActivity must not be null");
        this.observations = Objects.requireNonNull(observations, "observations must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static MentalStatusExam register(
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
            ProfessionalId createdBy) {
        MentalStatusExamId id = MentalStatusExamId.generate();
        LocalDateTime now = LocalDateTime.now();
        MentalStatusExam aggregate = new MentalStatusExam(
                id,
                encounterId,
                appearance,
                behavior,
                attitude,
                consciousness,
                orientation,
                attention,
                memory,
                speech,
                mood,
                affect,
                thoughtProcess,
                thoughtContent,
                perception,
                judgment,
                insight,
                psychomotorActivity,
                observations,
                createdBy,
                now);
        aggregate.recordEvent(new MentalStatusExamRegisteredEvent(id, now));
        return aggregate;
    }

    public static MentalStatusExam restore(
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
            ProfessionalId createdBy,
            LocalDateTime createdAt) {
        return new MentalStatusExam(
                id,
                encounterId,
                appearance,
                behavior,
                attitude,
                consciousness,
                orientation,
                attention,
                memory,
                speech,
                mood,
                affect,
                thoughtProcess,
                thoughtContent,
                perception,
                judgment,
                insight,
                psychomotorActivity,
                observations,
                createdBy,
                createdAt);
    }

    public void update(
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
            ProfessionalId createdBy) {
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.appearance = Objects.requireNonNull(appearance, "appearance must not be null");
        this.behavior = Objects.requireNonNull(behavior, "behavior must not be null");
        this.attitude = Objects.requireNonNull(attitude, "attitude must not be null");
        this.consciousness = Objects.requireNonNull(consciousness, "consciousness must not be null");
        this.orientation = Objects.requireNonNull(orientation, "orientation must not be null");
        this.attention = Objects.requireNonNull(attention, "attention must not be null");
        this.memory = Objects.requireNonNull(memory, "memory must not be null");
        this.speech = Objects.requireNonNull(speech, "speech must not be null");
        this.mood = Objects.requireNonNull(mood, "mood must not be null");
        this.affect = Objects.requireNonNull(affect, "affect must not be null");
        this.thoughtProcess = Objects.requireNonNull(thoughtProcess, "thoughtProcess must not be null");
        this.thoughtContent = Objects.requireNonNull(thoughtContent, "thoughtContent must not be null");
        this.perception = Objects.requireNonNull(perception, "perception must not be null");
        this.judgment = Objects.requireNonNull(judgment, "judgment must not be null");
        this.insight = Objects.requireNonNull(insight, "insight must not be null");
        this.psychomotorActivity = Objects.requireNonNull(psychomotorActivity, "psychomotorActivity must not be null");
        this.observations = Objects.requireNonNull(observations, "observations must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new MentalStatusExamUpdatedEvent(
                        this.id,
                        this.encounterId,
                        this.appearance,
                        this.behavior,
                        this.attitude,
                        this.consciousness,
                        this.orientation,
                        this.attention,
                        this.memory,
                        this.speech,
                        this.mood,
                        this.affect,
                        this.thoughtProcess,
                        this.thoughtContent,
                        this.perception,
                        this.judgment,
                        this.insight,
                        this.psychomotorActivity,
                        this.observations,
                        this.createdBy,
                        occurredOn));
    }

    public MentalStatusExamId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public String appearance() {
        return appearance;
    }

    public String behavior() {
        return behavior;
    }

    public String attitude() {
        return attitude;
    }

    public String consciousness() {
        return consciousness;
    }

    public String orientation() {
        return orientation;
    }

    public String attention() {
        return attention;
    }

    public String memory() {
        return memory;
    }

    public String speech() {
        return speech;
    }

    public String mood() {
        return mood;
    }

    public String affect() {
        return affect;
    }

    public String thoughtProcess() {
        return thoughtProcess;
    }

    public String thoughtContent() {
        return thoughtContent;
    }

    public String perception() {
        return perception;
    }

    public String judgment() {
        return judgment;
    }

    public String insight() {
        return insight;
    }

    public String psychomotorActivity() {
        return psychomotorActivity;
    }

    public String observations() {
        return observations;
    }

    public ProfessionalId createdBy() {
        return createdBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
