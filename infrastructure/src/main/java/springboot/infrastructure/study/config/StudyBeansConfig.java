package springboot.infrastructure.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.study.usecase.*;
import springboot.domain.study.port.repository.StudyRepository;
import springboot.infrastructure.study.adapters.out.persistence.mappers.StudyPersistenceMapper;
import springboot.infrastructure.study.adapters.out.persistence.repositories.StudyJpaRepository;
import springboot.infrastructure.study.adapters.out.persistence.repositories.StudyRepositoryAdapter;

@Configuration
public class StudyBeansConfig {
    @Bean public StudyPersistenceMapper studyPersistenceMapper() { return new StudyPersistenceMapper(); }
    @Bean public StudyRepository studyRepository(StudyJpaRepository repository, StudyPersistenceMapper mapper) {
        return new StudyRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterStudyUseCase registerStudyUseCase(StudyRepository r) { return new RegisterStudyUseCase(r); }
    @Bean public GetStudyByIdUseCase getStudyByIdUseCase(StudyRepository r) { return new GetStudyByIdUseCase(r); }
    @Bean public ListStudyUseCase listStudyUseCase(StudyRepository r) { return new ListStudyUseCase(r); }
    @Bean public UpdateStudyUseCase updateStudyUseCase(StudyRepository r) { return new UpdateStudyUseCase(r); }
    @Bean public DeleteStudyUseCase deleteStudyUseCase(StudyRepository r) { return new DeleteStudyUseCase(r); }
}
