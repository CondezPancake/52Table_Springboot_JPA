package springboot.infrastructure.stateregion.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "state_regions")
public class StateRegionJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "name_region", nullable = false, length = 50)
    private String nameRegion;

    @Column(name = "code_region", nullable = false, length = 10)
    private String codeRegion;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "country_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID countryId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public StateRegionJpaEntity() { }
    public StateRegionJpaEntity(
            UUID id,
            String nameRegion,
            String codeRegion,
            String description,
            boolean active,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.nameRegion = nameRegion;
        this.codeRegion = codeRegion;
        this.description = description;
        this.active = active;
        this.countryId = countryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
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

    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
