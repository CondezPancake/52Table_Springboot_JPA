package springboot.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

public interface PhoneContactJpaRepository extends JpaRepository<PhoneContactJpaEntity, UUID> {

}
