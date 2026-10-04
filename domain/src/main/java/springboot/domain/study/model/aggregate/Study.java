package springboot.domain.study.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.study.event.StudyRegisteredEvent;
import springboot.domain.study.event.StudyUpdatedEvent;
import springboot.domain.study.model.valueobject.StudyId;


public class Study extends AggregateRoot {
    private final StudyId id;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Study(
            StudyId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Study register(
            String name) {
        StudyId id = StudyId.generate();
        LocalDateTime now = LocalDateTime.now();
        Study aggregate = new Study(
                id,
                name,
                now,
                now);
        aggregate.recordEvent(new StudyRegisteredEvent(id, now));
        return aggregate;
    }

    public static Study restore(
            StudyId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Study(
                id,
                name,
                createdAt,
                updatedAt);
    }

    public void update(
            String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new StudyUpdatedEvent(
                        this.id,
                        this.name,
                        this.updatedAt));
    }

    public StudyId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
