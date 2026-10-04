package springboot.domain.consenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.consenttype.event.ConsentTypeRegisteredEvent;
import springboot.domain.consenttype.event.ConsentTypeUpdatedEvent;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;


public class ConsentType extends AggregateRoot {
    private final ConsentTypeId id;
    private String code;
    private String name;
    private boolean active;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConsentType(
            ConsentTypeId id,
            String code,
            String name,
            boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ConsentType register(
            String code,
            String name,
            boolean active,
            String description) {
        ConsentTypeId id = ConsentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        ConsentType aggregate = new ConsentType(
                id,
                code,
                name,
                active,
                description,
                now,
                now);
        aggregate.recordEvent(new ConsentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static ConsentType restore(
            ConsentTypeId id,
            String code,
            String name,
            boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConsentType(
                id,
                code,
                name,
                active,
                description,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name,
            boolean active,
            String description) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.active = active;
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ConsentTypeUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.description,
                        this.updatedAt));
    }

    public ConsentTypeId id() {
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

    public String description() {
        return description;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
