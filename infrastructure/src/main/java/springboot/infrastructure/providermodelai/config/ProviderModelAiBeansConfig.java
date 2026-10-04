package springboot.infrastructure.providermodelai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.providermodelai.usecase.*;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;
import springboot.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;
import springboot.infrastructure.providermodelai.adapters.out.persistence.repositories.ProviderModelAiJpaRepository;
import springboot.infrastructure.providermodelai.adapters.out.persistence.repositories.ProviderModelAiRepositoryAdapter;

@Configuration
public class ProviderModelAiBeansConfig {
    @Bean public ProviderModelAiPersistenceMapper providermodelaiPersistenceMapper() { return new ProviderModelAiPersistenceMapper(); }
    @Bean public ProviderModelAiRepository providermodelaiRepository(ProviderModelAiJpaRepository repository, ProviderModelAiPersistenceMapper mapper) {
        return new ProviderModelAiRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterProviderModelAiUseCase registerProviderModelAiUseCase(ProviderModelAiRepository r) { return new RegisterProviderModelAiUseCase(r); }
    @Bean public GetProviderModelAiByIdUseCase getProviderModelAiByIdUseCase(ProviderModelAiRepository r) { return new GetProviderModelAiByIdUseCase(r); }
    @Bean public ListProviderModelAiUseCase listProviderModelAiUseCase(ProviderModelAiRepository r) { return new ListProviderModelAiUseCase(r); }
    @Bean public UpdateProviderModelAiUseCase updateProviderModelAiUseCase(ProviderModelAiRepository r) { return new UpdateProviderModelAiUseCase(r); }
    @Bean public DeleteProviderModelAiUseCase deleteProviderModelAiUseCase(ProviderModelAiRepository r) { return new DeleteProviderModelAiUseCase(r); }
}
