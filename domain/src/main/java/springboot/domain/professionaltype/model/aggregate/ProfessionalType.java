package springboot.domain.professionaltype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.professionaltype.event.ProfessionalTypeRegisteredEvent;
import springboot.domain.professionaltype.event.ProfessionalTypeUpdatedEvent;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;


public class ProfessionalType extends AggregateRoot {
    private final ProfessionalTypeId id;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProfessionalType(
            ProfessionalTypeId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ProfessionalType register(
            String name) {
        ProfessionalTypeId id = ProfessionalTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        ProfessionalType aggregate = new ProfessionalType(
                id,
                name,
                now,
                now);
        aggregate.recordEvent(new ProfessionalTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static ProfessionalType restore(
            ProfessionalTypeId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProfessionalType(
                id,
                name,
                createdAt,
                updatedAt);
    }

    public void update(
            String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ProfessionalTypeUpdatedEvent(
                        this.id,
                        this.name,
                        this.updatedAt));
    }

    public ProfessionalTypeId id() {
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
