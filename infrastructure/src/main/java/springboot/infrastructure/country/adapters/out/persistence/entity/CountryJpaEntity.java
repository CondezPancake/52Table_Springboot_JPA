package springboot.infrastructure.country.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class CountryJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "name_country", nullable = false, length = 50)
    private String nameCountry;

    @Column(name = "code_country", nullable = false, length = 10)
    private String codeCountry;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "telephone_prefix", nullable = false, length = 5)
    private String telephonePrefix;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CountryJpaEntity() { }
    public CountryJpaEntity(
            UUID id,
            String nameCountry,
            String codeCountry,
            String description,
            boolean active,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.nameCountry = nameCountry;
        this.codeCountry = codeCountry;
        this.description = description;
        this.active = active;
        this.telephonePrefix = telephonePrefix;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTelephonePrefix() {
        return telephonePrefix;
    }

    public void setTelephonePrefix(String telephonePrefix) {
        this.telephonePrefix = telephonePrefix;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
