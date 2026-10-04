package springboot.infrastructure.citymunicipality.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "city_municipalities")
public class CityMunicipalityJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "name_city", nullable = false, length = 50)
    private String nameCity;

    @Column(name = "code_city", nullable = false, length = 10)
    private String codeCity;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "region_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID regionId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CityMunicipalityJpaEntity() { }
    public CityMunicipalityJpaEntity(
            UUID id,
            String nameCity,
            String codeCity,
            String description,
            boolean active,
            UUID regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.nameCity = nameCity;
        this.codeCity = codeCity;
        this.description = description;
        this.active = active;
        this.regionId = regionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getCodeCity() {
        return codeCity;
    }

    public void setCodeCity(String codeCity) {
        this.codeCity = codeCity;
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

    public UUID getRegionId() {
        return regionId;
    }

    public void setRegionId(UUID regionId) {
        this.regionId = regionId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
