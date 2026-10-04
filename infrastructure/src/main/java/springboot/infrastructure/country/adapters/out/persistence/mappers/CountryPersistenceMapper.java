package springboot.infrastructure.country.adapters.out.persistence.mappers;

import springboot.domain.country.model.aggregate.Country;
import springboot.domain.country.model.valueobject.CountryId;

import springboot.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

public class CountryPersistenceMapper {
    public CountryJpaEntity toJpa(Country domain) {
        if (domain == null) { return null; }
        CountryJpaEntity jpa = new CountryJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameCountry(domain.nameCountry());
        jpa.setCodeCountry(domain.codeCountry());
        jpa.setDescription(domain.description());
        jpa.setActive(domain.active());
        jpa.setTelephonePrefix(domain.telephonePrefix());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Country toDomain(CountryJpaEntity jpa) {
        if (jpa == null) { return null; }
        return Country.restore(
                new CountryId(jpa.getId()),
                jpa.getNameCountry(),
                jpa.getCodeCountry(),
                jpa.getDescription(),
                jpa.isActive(),
                jpa.getTelephonePrefix(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
