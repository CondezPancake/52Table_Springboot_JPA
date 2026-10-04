package springboot.domain.clinicalrecordstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.clinicalrecordstatus.event.ClinicalRecordStatusRegisteredEvent;
import springboot.domain.clinicalrecordstatus.event.ClinicalRecordStatusUpdatedEvent;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;


public class ClinicalRecordStatus extends AggregateRoot {
    private final ClinicalRecordStatusId id;
    private String code;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClinicalRecordStatus(
            ClinicalRecordStatusId id,
            String code,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ClinicalRecordStatus register(
            String code,
            String name) {
        ClinicalRecordStatusId id = ClinicalRecordStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecordStatus aggregate = new ClinicalRecordStatus(
                id,
                code,
                name,
                now,
                now);
        aggregate.recordEvent(new ClinicalRecordStatusRegisteredEvent(id, now));
        return aggregate;
    }

    public static ClinicalRecordStatus restore(
            ClinicalRecordStatusId id,
            String code,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClinicalRecordStatus(
                id,
                code,
                name,
                createdAt,
                updatedAt);
    }

    public void update(
            String code,
            String name) {
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ClinicalRecordStatusUpdatedEvent(
                        this.id,
                        this.code,
                        this.name,
                        this.updatedAt));
    }

    public ClinicalRecordStatusId id() {
        return id;
    }

    public String code() {
        return code;
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
