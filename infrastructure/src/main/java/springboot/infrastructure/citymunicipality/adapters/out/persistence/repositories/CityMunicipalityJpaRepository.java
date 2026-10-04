package springboot.infrastructure.citymunicipality.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

public interface CityMunicipalityJpaRepository extends JpaRepository<CityMunicipalityJpaEntity, UUID> {
    boolean existsByCodeCity(String code);
}
