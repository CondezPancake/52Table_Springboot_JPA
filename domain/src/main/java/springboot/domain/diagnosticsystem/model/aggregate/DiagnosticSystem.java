package springboot.domain.diagnosticsystem.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.diagnosticsystem.event.DiagnosticSystemRegisteredEvent;
import springboot.domain.diagnosticsystem.event.DiagnosticSystemUpdatedEvent;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;


public class DiagnosticSystem extends AggregateRoot {
    private final DiagnosticSystemId id;
    private String code;
    private String name;
    private boolean active;
    private String version;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DiagnosticSystem(
            DiagnosticSystemId id,
            String code,
            String name,
            boolean active,
            String version,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.version = Objects.requireNonNull(version, "version must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static DiagnosticSystem register(
            String code,
            String name,
            boolean active,
            String version) {
        DiagnosticSystemId id = DiagnosticSystemId.generate();
        LocalDateTime now = LocalDateTime.now();
        DiagnosticSystem aggregate = new DiagnosticSystem(
                id,
                code,
                name,
                active,
                version,
                now,
                now);
        aggregate.recordEvent(new DiagnosticSystemRegisteredEvent(id, now));
        return aggregate;
    }

    public static DiagnosticSystem restore(
            DiagnosticSystemId id,
            String code,
            String name,
            boolean active,
            String version,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DiagnosticSystem(
                id,
                code,
                name,
                active,
                version,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            boolean active,
            String version) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.version = Objects.requireNonNull(version, "version must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new DiagnosticSystemUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.version,
                        this.updatedAt));
    }

    public DiagnosticSystemId id() {
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

    public String version() {
        return version;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
