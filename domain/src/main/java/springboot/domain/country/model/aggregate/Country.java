package springboot.domain.country.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.country.event.CountryRegisteredEvent;
import springboot.domain.country.event.CountryUpdatedEvent;
import springboot.domain.country.model.valueobject.CountryId;


public class Country extends AggregateRoot {
    private final CountryId id;
    private String nameCountry;
    private String codeCountry;
    private String description;
    private boolean active;
    private String telephonePrefix;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Country(
            CountryId id,
            String nameCountry,
            String codeCountry,
            String description,
            boolean active,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameCountry = Objects.requireNonNull(nameCountry, "nameCountry must not be null");
        this.codeCountry = Objects.requireNonNull(codeCountry, "codeCountry must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.active = active;
        this.telephonePrefix = Objects.requireNonNull(telephonePrefix, "telephonePrefix must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Country register(
            String nameCountry,
            String codeCountry,
            String description,
            boolean active,
            String telephonePrefix) {
        CountryId id = CountryId.generate();
        LocalDateTime now = LocalDateTime.now();
        Country aggregate = new Country(
                id,
                nameCountry,
                codeCountry,
                description,
                active,
                telephonePrefix,
                now,
                now);
        aggregate.recordEvent(new CountryRegisteredEvent(id, now));
        return aggregate;
    }

    public static Country restore(
            CountryId id,
            String nameCountry,
            String codeCountry,
            String description,
            boolean active,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Country(
                id,
                nameCountry,
                codeCountry,
                description,
                active,
                telephonePrefix,
                createdAt,
                updatedAt);
    }

    public void update(
            String nameCountry,
            String codeCountry,
            String description,
            boolean active,
            String telephonePrefix) {
        this.nameCountry = Objects.requireNonNull(nameCountry, "nameCountry must not be null");
        this.codeCountry = Objects.requireNonNull(codeCountry, "codeCountry must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.active = active;
        this.telephonePrefix = Objects.requireNonNull(telephonePrefix, "telephonePrefix must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new CountryUpdatedEvent(
                        this.id,
                        this.nameCountry,
                        this.codeCountry,
                        this.description,
                        this.active,
                        this.telephonePrefix,
                        this.updatedAt));
    }

    public CountryId id() {
        return id;
    }

    public String nameCountry() {
        return nameCountry;
    }

    public String codeCountry() {
        return codeCountry;
    }

    public String description() {
        return description;
    }

    public boolean active() {
        return active;
    }

    public String telephonePrefix() {
        return telephonePrefix;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
