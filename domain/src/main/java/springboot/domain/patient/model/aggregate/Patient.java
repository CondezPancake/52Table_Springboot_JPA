package springboot.domain.patient.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.LocalDate;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.patient.event.PatientRegisteredEvent;
import springboot.domain.patient.event.PatientUpdatedEvent;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class Patient extends AggregateRoot {
    private final PatientId id;
    private DocumentTypeId documentTypeId;
    private String documentNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private GenderId biologicalSexId;
    private GenderId genderIdentityId;
    private String email;
    private String phone;
    private String address;
    private boolean active;
    private ProfessionalId createdBy;
    private ProfessionalId updatedBy;
    private CityMunicipalityId cityId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Patient(
            PatientId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            boolean active,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            CityMunicipalityId cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.documentTypeId = Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.middleName = middleName;
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.secondLastName = secondLastName;
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate must not be null");
        this.biologicalSexId = Objects.requireNonNull(biologicalSexId, "biologicalSexId must not be null");
        this.genderIdentityId = Objects.requireNonNull(genderIdentityId, "genderIdentityId must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.phone = Objects.requireNonNull(phone, "phone must not be null");
        this.address = Objects.requireNonNull(address, "address must not be null");
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.cityId = Objects.requireNonNull(cityId, "cityId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Patient register(
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            boolean active,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            CityMunicipalityId cityId) {
        PatientId id = PatientId.generate();
        LocalDateTime now = LocalDateTime.now();
        Patient aggregate = new Patient(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentityId,
                email,
                phone,
                address,
                active,
                createdBy,
                updatedBy,
                cityId,
                now,
                now);
        aggregate.recordEvent(new PatientRegisteredEvent(id, now));
        return aggregate;
    }

    public static Patient restore(
            PatientId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            boolean active,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            CityMunicipalityId cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Patient(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentityId,
                email,
                phone,
                address,
                active,
                createdBy,
                updatedBy,
                cityId,
                createdAt,
                updatedAt);
    }

    public void update(
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            boolean active,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            CityMunicipalityId cityId) {
        this.documentTypeId = Objects.requireNonNull(documentTypeId, "documentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.middleName = middleName;
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.secondLastName = secondLastName;
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate must not be null");
        this.biologicalSexId = Objects.requireNonNull(biologicalSexId, "biologicalSexId must not be null");
        this.genderIdentityId = Objects.requireNonNull(genderIdentityId, "genderIdentityId must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.phone = Objects.requireNonNull(phone, "phone must not be null");
        this.address = Objects.requireNonNull(address, "address must not be null");
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.cityId = Objects.requireNonNull(cityId, "cityId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new PatientUpdatedEvent(
                        this.id,
                        this.documentTypeId,
                        this.documentNumber,
                        this.firstName,
                        this.middleName,
                        this.lastName,
                        this.secondLastName,
                        this.birthDate,
                        this.biologicalSexId,
                        this.genderIdentityId,
                        this.email,
                        this.phone,
                        this.address,
                        this.active,
                        this.createdBy,
                        this.updatedBy,
                        this.cityId,
                        this.updatedAt));
    }

    public PatientId id() {
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

    public String middleName() {
        return middleName;
    }

    public String lastName() {
        return lastName;
    }

    public String secondLastName() {
        return secondLastName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public GenderId biologicalSexId() {
        return biologicalSexId;
    }

    public GenderId genderIdentityId() {
        return genderIdentityId;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String address() {
        return address;
    }

    public boolean active() {
        return active;
    }

    public ProfessionalId createdBy() {
        return createdBy;
    }

    public ProfessionalId updatedBy() {
        return updatedBy;
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
