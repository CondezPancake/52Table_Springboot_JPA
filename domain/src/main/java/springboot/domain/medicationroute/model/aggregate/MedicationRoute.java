package springboot.domain.medicationroute.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.medicationroute.event.MedicationRouteRegisteredEvent;
import springboot.domain.medicationroute.event.MedicationRouteUpdatedEvent;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;


public class MedicationRoute extends AggregateRoot {
    private final MedicationRouteId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MedicationRoute(
            MedicationRouteId id,
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

    public static MedicationRoute register(
            String code,
            String name,
            boolean active) {
        MedicationRouteId id = MedicationRouteId.generate();
        LocalDateTime now = LocalDateTime.now();
        MedicationRoute aggregate = new MedicationRoute(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new MedicationRouteRegisteredEvent(id, now));
        return aggregate;
    }

    public static MedicationRoute restore(
            MedicationRouteId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new MedicationRoute(
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
        recordEvent(new MedicationRouteUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public MedicationRouteId id() {
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
