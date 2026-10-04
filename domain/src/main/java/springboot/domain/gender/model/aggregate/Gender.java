package springboot.domain.gender.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.gender.event.GenderRegisteredEvent;
import springboot.domain.gender.event.GenderUpdatedEvent;
import springboot.domain.gender.model.valueobject.GenderId;


public class Gender extends AggregateRoot {
    private final GenderId id;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Gender(
            GenderId id,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Gender register(
            String description) {
        GenderId id = GenderId.generate();
        LocalDateTime now = LocalDateTime.now();
        Gender aggregate = new Gender(
                id,
                description,
                now,
                now);
        aggregate.recordEvent(new GenderRegisteredEvent(id, now));
        return aggregate;
    }

    public static Gender restore(
            GenderId id,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Gender(
                id,
                description,
                createdAt,
                updatedAt);
    }

    public void update(
            String description) {
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new GenderUpdatedEvent(
                        this.id,
                        this.description,
                        this.updatedAt));
    }

    public GenderId id() {
        return id;
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
