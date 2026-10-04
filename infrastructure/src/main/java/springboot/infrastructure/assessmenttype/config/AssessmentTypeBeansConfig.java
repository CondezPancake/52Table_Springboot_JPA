package springboot.infrastructure.assessmenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.assessmenttype.usecase.*;
import springboot.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import springboot.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;
import springboot.infrastructure.assessmenttype.adapters.out.persistence.repositories.AssessmentTypeJpaRepository;
import springboot.infrastructure.assessmenttype.adapters.out.persistence.repositories.AssessmentTypeRepositoryAdapter;

@Configuration
public class AssessmentTypeBeansConfig {
    @Bean public AssessmentTypePersistenceMapper assessmenttypePersistenceMapper() { return new AssessmentTypePersistenceMapper(); }
    @Bean public AssessmentTypeRepository assessmenttypeRepository(AssessmentTypeJpaRepository repository, AssessmentTypePersistenceMapper mapper) {
        return new AssessmentTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterAssessmentTypeUseCase registerAssessmentTypeUseCase(AssessmentTypeRepository r) { return new RegisterAssessmentTypeUseCase(r); }
    @Bean public GetAssessmentTypeByIdUseCase getAssessmentTypeByIdUseCase(AssessmentTypeRepository r) { return new GetAssessmentTypeByIdUseCase(r); }
    @Bean public ListAssessmentTypeUseCase listAssessmentTypeUseCase(AssessmentTypeRepository r) { return new ListAssessmentTypeUseCase(r); }
    @Bean public UpdateAssessmentTypeUseCase updateAssessmentTypeUseCase(AssessmentTypeRepository r) { return new UpdateAssessmentTypeUseCase(r); }
    @Bean public DeleteAssessmentTypeUseCase deleteAssessmentTypeUseCase(AssessmentTypeRepository r) { return new DeleteAssessmentTypeUseCase(r); }
}
