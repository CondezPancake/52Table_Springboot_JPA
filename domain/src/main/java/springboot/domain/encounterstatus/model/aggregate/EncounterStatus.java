package springboot.domain.encounterstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.encounterstatus.event.EncounterStatusRegisteredEvent;
import springboot.domain.encounterstatus.event.EncounterStatusUpdatedEvent;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;


public class EncounterStatus extends AggregateRoot {
    private final EncounterStatusId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterStatus(
            EncounterStatusId id,
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

    public static EncounterStatus register(
            String code,
            String name,
            boolean active) {
        EncounterStatusId id = EncounterStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        EncounterStatus aggregate = new EncounterStatus(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new EncounterStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static EncounterStatus restore(
            EncounterStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterStatus(
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
        recordEvent(new EncounterStatusUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public EncounterStatusId id() {
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
