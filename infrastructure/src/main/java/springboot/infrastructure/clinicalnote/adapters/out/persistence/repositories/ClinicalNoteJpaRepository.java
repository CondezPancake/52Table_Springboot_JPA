package springboot.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;

public interface ClinicalNoteJpaRepository extends JpaRepository<ClinicalNoteJpaEntity, UUID> {

}
