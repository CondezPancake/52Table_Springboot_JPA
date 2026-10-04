package springboot.infrastructure.country.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

public interface CountryJpaRepository extends JpaRepository<CountryJpaEntity, UUID> {
    boolean existsByCodeCountry(String code);
}
