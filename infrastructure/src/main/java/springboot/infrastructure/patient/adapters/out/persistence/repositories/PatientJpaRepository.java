package springboot.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;

public interface PatientJpaRepository extends JpaRepository<PatientJpaEntity, UUID> {

}
