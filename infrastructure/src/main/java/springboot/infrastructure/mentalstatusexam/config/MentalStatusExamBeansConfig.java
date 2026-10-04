package springboot.infrastructure.mentalstatusexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.mentalstatusexam.usecase.*;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import springboot.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;
import springboot.infrastructure.mentalstatusexam.adapters.out.persistence.repositories.MentalStatusExamJpaRepository;
import springboot.infrastructure.mentalstatusexam.adapters.out.persistence.repositories.MentalStatusExamRepositoryAdapter;

@Configuration
public class MentalStatusExamBeansConfig {
    @Bean public MentalStatusExamPersistenceMapper mentalstatusexamPersistenceMapper() { return new MentalStatusExamPersistenceMapper(); }
    @Bean public MentalStatusExamRepository mentalstatusexamRepository(MentalStatusExamJpaRepository repository, MentalStatusExamPersistenceMapper mapper) {
        return new MentalStatusExamRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterMentalStatusExamUseCase registerMentalStatusExamUseCase(MentalStatusExamRepository r) { return new RegisterMentalStatusExamUseCase(r); }
    @Bean public GetMentalStatusExamByIdUseCase getMentalStatusExamByIdUseCase(MentalStatusExamRepository r) { return new GetMentalStatusExamByIdUseCase(r); }
    @Bean public ListMentalStatusExamUseCase listMentalStatusExamUseCase(MentalStatusExamRepository r) { return new ListMentalStatusExamUseCase(r); }
    @Bean public UpdateMentalStatusExamUseCase updateMentalStatusExamUseCase(MentalStatusExamRepository r) { return new UpdateMentalStatusExamUseCase(r); }
    @Bean public DeleteMentalStatusExamUseCase deleteMentalStatusExamUseCase(MentalStatusExamRepository r) { return new DeleteMentalStatusExamUseCase(r); }
}
