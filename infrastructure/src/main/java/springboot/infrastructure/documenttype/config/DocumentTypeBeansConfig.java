package springboot.infrastructure.documenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.documenttype.usecase.*;
import springboot.domain.documenttype.port.repository.DocumentTypeRepository;
import springboot.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;
import springboot.infrastructure.documenttype.adapters.out.persistence.repositories.DocumentTypeJpaRepository;
import springboot.infrastructure.documenttype.adapters.out.persistence.repositories.DocumentTypeRepositoryAdapter;

@Configuration
public class DocumentTypeBeansConfig {
    @Bean public DocumentTypePersistenceMapper documenttypePersistenceMapper() { return new DocumentTypePersistenceMapper(); }
    @Bean public DocumentTypeRepository documenttypeRepository(DocumentTypeJpaRepository repository, DocumentTypePersistenceMapper mapper) {
        return new DocumentTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterDocumentTypeUseCase registerDocumentTypeUseCase(DocumentTypeRepository r) { return new RegisterDocumentTypeUseCase(r); }
    @Bean public GetDocumentTypeByIdUseCase getDocumentTypeByIdUseCase(DocumentTypeRepository r) { return new GetDocumentTypeByIdUseCase(r); }
    @Bean public ListDocumentTypeUseCase listDocumentTypeUseCase(DocumentTypeRepository r) { return new ListDocumentTypeUseCase(r); }
    @Bean public UpdateDocumentTypeUseCase updateDocumentTypeUseCase(DocumentTypeRepository r) { return new UpdateDocumentTypeUseCase(r); }
    @Bean public DeleteDocumentTypeUseCase deleteDocumentTypeUseCase(DocumentTypeRepository r) { return new DeleteDocumentTypeUseCase(r); }
}
