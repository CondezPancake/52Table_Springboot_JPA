package springboot.domain.encountertype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.encountertype.event.EncounterTypeRegisteredEvent;
import springboot.domain.encountertype.event.EncounterTypeUpdatedEvent;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;


public class EncounterType extends AggregateRoot {
    private final EncounterTypeId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterType(
            EncounterTypeId id,
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

    public static EncounterType register(
            String code,
            String name,
            boolean active) {
        EncounterTypeId id = EncounterTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        EncounterType aggregate = new EncounterType(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new EncounterTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static EncounterType restore(
            EncounterTypeId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterType(
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
        recordEvent(new EncounterTypeUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public EncounterTypeId id() {
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
