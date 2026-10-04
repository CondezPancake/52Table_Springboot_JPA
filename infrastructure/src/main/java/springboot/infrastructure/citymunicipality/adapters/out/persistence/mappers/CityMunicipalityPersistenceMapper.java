package springboot.infrastructure.citymunicipality.adapters.out.persistence.mappers;

import springboot.domain.citymunicipality.model.aggregate.CityMunicipality;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

public class CityMunicipalityPersistenceMapper {
    public CityMunicipalityJpaEntity toJpa(CityMunicipality domain) {
        if (domain == null) { return null; }
        CityMunicipalityJpaEntity jpa = new CityMunicipalityJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameCity(domain.nameCity());
        jpa.setCodeCity(domain.codeCity());
        jpa.setDescription(domain.description());
        jpa.setActive(domain.active());
        jpa.setRegionId(domain.regionId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public CityMunicipality toDomain(CityMunicipalityJpaEntity jpa) {
        if (jpa == null) { return null; }
        return CityMunicipality.restore(
                new CityMunicipalityId(jpa.getId()),
                jpa.getNameCity(),
                jpa.getCodeCity(),
                jpa.getDescription(),
                jpa.isActive(),
                new StateRegionId(jpa.getRegionId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
