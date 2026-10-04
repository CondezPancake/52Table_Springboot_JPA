package springboot.domain.risklevel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.risklevel.event.RiskLevelRegisteredEvent;
import springboot.domain.risklevel.event.RiskLevelUpdatedEvent;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;


public class RiskLevel extends AggregateRoot {
    private final RiskLevelId id;
    private String code;
    private String name;
    private boolean active;
    private int severity;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private RiskLevel(
            RiskLevelId id,
            String code,
            String name,
            boolean active,
            int severity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.severity = severity;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static RiskLevel register(
            String code,
            String name,
            boolean active,
            int severity) {
        RiskLevelId id = RiskLevelId.generate();
        LocalDateTime now = LocalDateTime.now();
        RiskLevel aggregate = new RiskLevel(
                id,
                code,
                name,
                active,
                severity,
                now,
                now);
        aggregate.recordEvent(new RiskLevelRegisteredEvent(id, now));
        return aggregate;
    }

    public static RiskLevel restore(
            RiskLevelId id,
            String code,
            String name,
            boolean active,
            int severity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new RiskLevel(
                id,
                code,
                name,
                active,
                severity,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            boolean active,
            int severity) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.severity = severity;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new RiskLevelUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.severity,
                        this.updatedAt));
    }

    public RiskLevelId id() {
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

    public int severity() {
        return severity;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
