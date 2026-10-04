package springboot.domain.encountermodality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.encountermodality.event.EncounterModalityRegisteredEvent;
import springboot.domain.encountermodality.event.EncounterModalityUpdatedEvent;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;


public class EncounterModality extends AggregateRoot {
    private final EncounterModalityId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterModality(
            EncounterModalityId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static EncounterModality register(
            String code,
            String name,
            boolean active) {
        EncounterModalityId id = EncounterModalityId.generate();
        LocalDateTime now = LocalDateTime.now();
        EncounterModality aggregate = new EncounterModality(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new EncounterModalityRegisteredEvent(id, now));
        return aggregate;
    }

    public static EncounterModality restore(
            EncounterModalityId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterModality(
                id,
                code,
                name,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            boolean active) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new EncounterModalityUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public EncounterModalityId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
