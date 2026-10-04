package springboot.infrastructure.sendertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.sendertype.usecase.*;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;
import springboot.infrastructure.sendertype.adapters.out.persistence.mappers.SenderTypePersistenceMapper;
import springboot.infrastructure.sendertype.adapters.out.persistence.repositories.SenderTypeJpaRepository;
import springboot.infrastructure.sendertype.adapters.out.persistence.repositories.SenderTypeRepositoryAdapter;

@Configuration
public class SenderTypeBeansConfig {
    @Bean public SenderTypePersistenceMapper sendertypePersistenceMapper() { return new SenderTypePersistenceMapper(); }
    @Bean public SenderTypeRepository sendertypeRepository(SenderTypeJpaRepository repository, SenderTypePersistenceMapper mapper) {
        return new SenderTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterSenderTypeUseCase registerSenderTypeUseCase(SenderTypeRepository r) { return new RegisterSenderTypeUseCase(r); }
    @Bean public GetSenderTypeByIdUseCase getSenderTypeByIdUseCase(SenderTypeRepository r) { return new GetSenderTypeByIdUseCase(r); }
    @Bean public ListSenderTypeUseCase listSenderTypeUseCase(SenderTypeRepository r) { return new ListSenderTypeUseCase(r); }
    @Bean public UpdateSenderTypeUseCase updateSenderTypeUseCase(SenderTypeRepository r) { return new UpdateSenderTypeUseCase(r); }
    @Bean public DeleteSenderTypeUseCase deleteSenderTypeUseCase(SenderTypeRepository r) { return new DeleteSenderTypeUseCase(r); }
}
