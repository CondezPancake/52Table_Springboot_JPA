package springboot.infrastructure.relationshiptype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.relationshiptype.usecase.*;
import springboot.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import springboot.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;
import springboot.infrastructure.relationshiptype.adapters.out.persistence.repositories.RelationshipTypeJpaRepository;
import springboot.infrastructure.relationshiptype.adapters.out.persistence.repositories.RelationshipTypeRepositoryAdapter;

@Configuration
public class RelationshipTypeBeansConfig {
    @Bean public RelationshipTypePersistenceMapper relationshiptypePersistenceMapper() { return new RelationshipTypePersistenceMapper(); }
    @Bean public RelationshipTypeRepository relationshiptypeRepository(RelationshipTypeJpaRepository repository, RelationshipTypePersistenceMapper mapper) {
        return new RelationshipTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterRelationshipTypeUseCase registerRelationshipTypeUseCase(RelationshipTypeRepository r) { return new RegisterRelationshipTypeUseCase(r); }
    @Bean public GetRelationshipTypeByIdUseCase getRelationshipTypeByIdUseCase(RelationshipTypeRepository r) { return new GetRelationshipTypeByIdUseCase(r); }
    @Bean public ListRelationshipTypeUseCase listRelationshipTypeUseCase(RelationshipTypeRepository r) { return new ListRelationshipTypeUseCase(r); }
    @Bean public UpdateRelationshipTypeUseCase updateRelationshipTypeUseCase(RelationshipTypeRepository r) { return new UpdateRelationshipTypeUseCase(r); }
    @Bean public DeleteRelationshipTypeUseCase deleteRelationshipTypeUseCase(RelationshipTypeRepository r) { return new DeleteRelationshipTypeUseCase(r); }
}
