package springboot.domain.professional.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.professional.event.ProfessionalRegisteredEvent;
import springboot.domain.professional.event.ProfessionalUpdatedEvent;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class Professional extends AggregateRoot {
    private final ProfessionalId id;
    private DocumentTypeId documentTypeId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private ProfessionalTypeId professionalTypeId;
    private String licenseNumber;
    private boolean active;
    private CityMunicipalityId cityId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Professional(
            ProfessionalId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            boolean active,
            CityMunicipalityId cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.documentTypeId = Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.professionalTypeId = Objects.requireNonNull(professionalTypeId, "professionalTypeId must not be null");
        this.licenseNumber = Objects.requireNonNull(licenseNumber, "licenseNumber must not be null");
        this.active = active;
        this.cityId = Objects.requireNonNull(cityId, "cityId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Professional register(
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            boolean active,
            CityMunicipalityId cityId) {
        ProfessionalId id = ProfessionalId.generate();
        LocalDateTime now = LocalDateTime.now();
        Professional aggregate = new Professional(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                lastName,
                professionalTypeId,
                licenseNumber,
                active,
                cityId,
                now,
                now);
        aggregate.recordEvent(new ProfessionalRegisteredEvent(id, now));
        return aggregate;
    }

    public static Professional restore(
            ProfessionalId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            boolean active,
            CityMunicipalityId cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Professional(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                lastName,
                professionalTypeId,
                licenseNumber,
                active,
                cityId,
                createdAt,
                updatedAt);
    }

    public void update(
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            boolean active,
            CityMunicipalityId cityId) {
        this.documentTypeId = Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.professionalTypeId = Objects.requireNonNull(professionalTypeId, "professionalTypeId must not be null");
        this.licenseNumber = Objects.requireNonNull(licenseNumber, "licenseNumber must not be null");
        this.active = active;
        this.cityId = Objects.requireNonNull(cityId, "cityId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ProfessionalUpdatedEvent(
                        this.id,
                        this.documentTypeId,
                        this.documentNumber,
                        this.firstName,
                        this.lastName,
                        this.professionalTypeId,
                        this.licenseNumber,
                        this.active,
                        this.cityId,
                        this.updatedAt));
    }

    public ProfessionalId id() {
        return id;
    }

    public DocumentTypeId documentTypeId() {
        return documentTypeId;
    }

    public String documentNumber() {
        return documentNumber;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public ProfessionalTypeId professionalTypeId() {
        return professionalTypeId;
    }

    public String licenseNumber() {
        return licenseNumber;
    }

    public boolean active() {
        return active;
    }

    public CityMunicipalityId cityId() {
        return cityId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
