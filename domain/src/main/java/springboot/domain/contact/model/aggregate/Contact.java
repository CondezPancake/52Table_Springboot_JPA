package springboot.domain.contact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.contact.event.ContactRegisteredEvent;
import springboot.domain.contact.event.ContactUpdatedEvent;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class Contact extends AggregateRoot {
    private final ContactId id;
    private String fullName;
    private String email;
    private String notes;
    private CityMunicipalityId cityId;
    private ProfessionalId createdBy;
    private ProfessionalId updatedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Contact(
            ContactId id,
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.fullName = Objects.requireNonNull(fullName, "fullName must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.notes = Objects.requireNonNull(notes, "notes must not be null");
        this.cityId = Objects.requireNonNull(cityId, "cityId must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.updatedBy = updatedBy;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Contact register(
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy) {
        ContactId id = ContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        Contact aggregate = new Contact(
                id,
                fullName,
                email,
                notes,
                cityId,
                createdBy,
                updatedBy,
                now,
                now);
        aggregate.recordEvent(new ContactRegisteredEvent(id, now));
        return aggregate;
    }

    public static Contact restore(
            ContactId id,
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Contact(
                id,
                fullName,
                email,
                notes,
                cityId,
                createdBy,
                updatedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy) {
        this.fullName = Objects.requireNonNull(fullName, "fullName must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.notes = Objects.requireNonNull(notes, "notes must not be null");
        this.cityId = Objects.requireNonNull(cityId, "cityId must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ContactUpdatedEvent(
                        this.id,
                        this.fullName,
                        this.email,
                        this.notes,
                        this.cityId,
                        this.createdBy,
                        this.updatedBy,
                        this.updatedAt));
    }

    public ContactId id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    public String notes() {
        return notes;
    }

    public CityMunicipalityId cityId() {
        return cityId;
    }

    public ProfessionalId createdBy() {
        return createdBy;
    }

    public ProfessionalId updatedBy() {
        return updatedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
