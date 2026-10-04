package springboot.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;

public interface ProviderModelAiJpaRepository extends JpaRepository<ProviderModelAiJpaEntity, UUID> {

}
