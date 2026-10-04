package springboot.domain.citymunicipality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.citymunicipality.event.CityMunicipalityRegisteredEvent;
import springboot.domain.citymunicipality.event.CityMunicipalityUpdatedEvent;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.stateregion.model.valueobject.StateRegionId;

public class CityMunicipality extends AggregateRoot {
    private final CityMunicipalityId id;
    private String nameCity;
    private String codeCity;
    private String description;
    private boolean active;
    private StateRegionId regionId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CityMunicipality(
            CityMunicipalityId id,
            String nameCity,
            String codeCity,
            String description,
            boolean active,
            StateRegionId regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameCity = Objects.requireNonNull(nameCity, "nameCity must not be null");
        this.codeCity = Objects.requireNonNull(codeCity, "codeCity must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.active = active;
        this.regionId = Objects.requireNonNull(regionId, "regionId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static CityMunicipality register(
            String nameCity,
            String codeCity,
            String description,
            boolean active,
            StateRegionId regionId) {
        CityMunicipalityId id = CityMunicipalityId.generate();
        LocalDateTime now = LocalDateTime.now();
        CityMunicipality aggregate = new CityMunicipality(
                id,
                nameCity,
                codeCity,
                description,
                active,
                regionId,
                now,
                now);
        aggregate.recordEvent(new CityMunicipalityRegisteredEvent(id, now));
        return aggregate;
    }

    public static CityMunicipality restore(
            CityMunicipalityId id,
            String nameCity,
            String codeCity,
            String description,
            boolean active,
            StateRegionId regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CityMunicipality(
                id,
                nameCity,
                codeCity,
                description,
                active,
                regionId,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameCity,
            String codeCity,
            String description,
            boolean active,
            StateRegionId regionId) {
        this.nameCity = Objects.requireNonNull(nameCity, "nameCity must not be null");
        this.codeCity = Objects.requireNonNull(codeCity, "codeCity must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.active = active;
        this.regionId = Objects.requireNonNull(regionId, "regionId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new CityMunicipalityUpdatedEvent(
                        this.id,
                        this.nameCity,
                        this.codeCity,
                        this.description,
                        this.active,
                        this.regionId,
                        this.updatedAt));
    }

    public CityMunicipalityId id() {
        return id;
    }

    public String nameCity() {
        return nameCity;
    }

    public String codeCity() {
        return codeCity;
    }

    public String description() {
        return description;
    }

    public boolean active() {
        return active;
    }

    public StateRegionId regionId() {
        return regionId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
