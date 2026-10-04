package springboot.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.providermodelai.model.aggregate.ProviderModelAi;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;
import springboot.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;
import springboot.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;

public class ProviderModelAiRepositoryAdapter implements ProviderModelAiRepository {
    private final ProviderModelAiJpaRepository jpaRepository;
    private final ProviderModelAiPersistenceMapper mapper;
    public ProviderModelAiRepositoryAdapter(ProviderModelAiJpaRepository jpaRepository, ProviderModelAiPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public ProviderModelAi save(ProviderModelAi aggregate) {
        ProviderModelAiJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<ProviderModelAi> findById(ProviderModelAiId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<ProviderModelAi> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(ProviderModelAi aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
