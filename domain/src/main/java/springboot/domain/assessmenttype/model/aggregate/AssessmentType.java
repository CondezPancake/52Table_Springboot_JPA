package springboot.domain.assessmenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.assessmenttype.event.AssessmentTypeRegisteredEvent;
import springboot.domain.assessmenttype.event.AssessmentTypeUpdatedEvent;
import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;


public class AssessmentType extends AggregateRoot {
    private final AssessmentTypeId id;
    private String code;
    private String name;
    private boolean active;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AssessmentType(
            AssessmentTypeId id,
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

    public static AssessmentType register(
            String code,
            String name,
            boolean active,
            String description) {
        AssessmentTypeId id = AssessmentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        AssessmentType aggregate = new AssessmentType(
                id,
                code,
                name,
                active,
                description,
                now,
                now);
        aggregate.recordEvent(new AssessmentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static AssessmentType restore(
            AssessmentTypeId id,
            String code,
            String name,
            boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AssessmentType(
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
        recordEvent(new AssessmentTypeUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.description,
                        this.updatedAt));
    }

    public AssessmentTypeId id() {
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
