package springboot.infrastructure.stateregion.adapters.out.persistence.mappers;

import springboot.domain.stateregion.model.aggregate.StateRegion;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

public class StateRegionPersistenceMapper {
    public StateRegionJpaEntity toJpa(StateRegion domain) {
        if (domain == null) { return null; }
        StateRegionJpaEntity jpa = new StateRegionJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameRegion(domain.nameRegion());
        jpa.setCodeRegion(domain.codeRegion());
        jpa.setDescription(domain.description());
        jpa.setActive(domain.active());
        jpa.setCountryId(domain.countryId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public StateRegion toDomain(StateRegionJpaEntity jpa) {
        if (jpa == null) { return null; }
        return StateRegion.restore(
                new StateRegionId(jpa.getId()),
                jpa.getNameRegion(),
                jpa.getCodeRegion(),
                jpa.getDescription(),
                jpa.isActive(),
                new CountryId(jpa.getCountryId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
