package springboot.infrastructure.professional.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "professionals", uniqueConstraints = { @UniqueConstraint(name = "uk_professionals_document_number", columnNames = "document_number"), @UniqueConstraint(name = "uk_professionals_first_name", columnNames = "first_name"), @UniqueConstraint(name = "uk_professionals_last_name", columnNames = "last_name"), @UniqueConstraint(name = "uk_professionals_license_number", columnNames = "license_number") })
public class ProfessionalJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "document_type_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID documentTypeId;

    @Column(name = "document_number", nullable = false, length = 30)
    private String documentNumber;

    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "professional_type", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID professionalTypeId;

    @Column(name = "license_number", nullable = false, length = 100)
    private String licenseNumber;

    @Column(name = "active", nullable = false)
    private boolean active;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "city_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID cityId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProfessionalJpaEntity() { }
    public ProfessionalJpaEntity(
            UUID id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalTypeId,
            String licenseNumber,
            boolean active,
            UUID cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalTypeId = professionalTypeId;
        this.licenseNumber = licenseNumber;
        this.active = active;
        this.cityId = cityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(UUID documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getProfessionalTypeId() {
        return professionalTypeId;
    }

    public void setProfessionalTypeId(UUID professionalTypeId) {
        this.professionalTypeId = professionalTypeId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
