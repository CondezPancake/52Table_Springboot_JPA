package springboot.domain.providermodelai.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.providermodelai.model.aggregate.ProviderModelAi;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public interface ProviderModelAiRepository {
    ProviderModelAi save(ProviderModelAi aggregate);
    Optional<ProviderModelAi> findById(ProviderModelAiId id);
    List<ProviderModelAi> findAll();

    void delete(ProviderModelAi aggregate);
}
