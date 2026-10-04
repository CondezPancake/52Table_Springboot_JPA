package springboot.domain.documenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.documenttype.event.DocumentTypeRegisteredEvent;
import springboot.domain.documenttype.event.DocumentTypeUpdatedEvent;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;


public class DocumentType extends AggregateRoot {
    private final DocumentTypeId id;
    private String code;
    private String name;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DocumentType(
            DocumentTypeId id,
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

    public static DocumentType register(
            String code,
            String name,
            boolean active) {
        DocumentTypeId id = DocumentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        DocumentType aggregate = new DocumentType(
                id,
                code,
                name,
                active,
                now,
                now);
        aggregate.recordEvent(new DocumentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static DocumentType restore(
            DocumentTypeId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DocumentType(
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
        recordEvent(new DocumentTypeUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.active,
                        this.updatedAt));
    }

    public DocumentTypeId id() {
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
