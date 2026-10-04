package springboot.infrastructure.diagnosticsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.diagnosticsystem.usecase.*;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import springboot.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;
import springboot.infrastructure.diagnosticsystem.adapters.out.persistence.repositories.DiagnosticSystemJpaRepository;
import springboot.infrastructure.diagnosticsystem.adapters.out.persistence.repositories.DiagnosticSystemRepositoryAdapter;

@Configuration
public class DiagnosticSystemBeansConfig {
    @Bean public DiagnosticSystemPersistenceMapper diagnosticsystemPersistenceMapper() { return new DiagnosticSystemPersistenceMapper(); }
    @Bean public DiagnosticSystemRepository diagnosticsystemRepository(DiagnosticSystemJpaRepository repository, DiagnosticSystemPersistenceMapper mapper) {
        return new DiagnosticSystemRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterDiagnosticSystemUseCase registerDiagnosticSystemUseCase(DiagnosticSystemRepository r) { return new RegisterDiagnosticSystemUseCase(r); }
    @Bean public GetDiagnosticSystemByIdUseCase getDiagnosticSystemByIdUseCase(DiagnosticSystemRepository r) { return new GetDiagnosticSystemByIdUseCase(r); }
    @Bean public ListDiagnosticSystemUseCase listDiagnosticSystemUseCase(DiagnosticSystemRepository r) { return new ListDiagnosticSystemUseCase(r); }
    @Bean public UpdateDiagnosticSystemUseCase updateDiagnosticSystemUseCase(DiagnosticSystemRepository r) { return new UpdateDiagnosticSystemUseCase(r); }
    @Bean public DeleteDiagnosticSystemUseCase deleteDiagnosticSystemUseCase(DiagnosticSystemRepository r) { return new DeleteDiagnosticSystemUseCase(r); }
}
