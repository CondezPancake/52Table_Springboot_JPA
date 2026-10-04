package springboot.domain.stateregion.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.stateregion.event.StateRegionRegisteredEvent;
import springboot.domain.stateregion.event.StateRegionUpdatedEvent;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.country.model.valueobject.CountryId;

public class StateRegion extends AggregateRoot {
    private final StateRegionId id;
    private String nameRegion;
    private String codeRegion;
    private String description;
    private boolean active;
    private CountryId countryId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StateRegion(
            StateRegionId id,
            String nameRegion,
            String codeRegion,
            String description,
            boolean active,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameRegion = Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        this.codeRegion = Objects.requireNonNull(codeRegion, "codeRegion must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.active = active;
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static StateRegion register(
            String nameRegion,
            String codeRegion,
            String description,
            boolean active,
            CountryId countryId) {
        StateRegionId id = StateRegionId.generate();
        LocalDateTime now = LocalDateTime.now();
        StateRegion aggregate = new StateRegion(
                id,
                nameRegion,
                codeRegion,
                description,
                active,
                countryId,
                now,
                now);
        aggregate.recordEvent(new StateRegionRegisteredEvent(id, now));
        return aggregate;
    }

    public static StateRegion restore(
            StateRegionId id,
            String nameRegion,
            String codeRegion,
            String description,
            boolean active,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new StateRegion(
                id,
                nameRegion,
                codeRegion,
                description,
                active,
                countryId,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameRegion,
            String codeRegion,
            String description,
            boolean active,
            CountryId countryId) {
        this.nameRegion = Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        this.codeRegion = Objects.requireNonNull(codeRegion, "codeRegion must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.active = active;
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new StateRegionUpdatedEvent(
                        this.id,
                        this.nameRegion,
                        this.codeRegion,
                        this.description,
                        this.active,
                        this.countryId,
                        this.updatedAt));
    }

    public StateRegionId id() {
        return id;
    }

    public String nameRegion() {
        return nameRegion;
    }

    public String codeRegion() {
        return codeRegion;
    }

    public String description() {
        return description;
    }

    public boolean active() {
        return active;
    }

    public CountryId countryId() {
        return countryId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
