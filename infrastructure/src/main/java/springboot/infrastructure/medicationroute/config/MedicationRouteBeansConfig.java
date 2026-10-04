package springboot.infrastructure.medicationroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.medicationroute.usecase.*;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;
import springboot.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;
import springboot.infrastructure.medicationroute.adapters.out.persistence.repositories.MedicationRouteJpaRepository;
import springboot.infrastructure.medicationroute.adapters.out.persistence.repositories.MedicationRouteRepositoryAdapter;

@Configuration
public class MedicationRouteBeansConfig {
    @Bean public MedicationRoutePersistenceMapper medicationroutePersistenceMapper() { return new MedicationRoutePersistenceMapper(); }
    @Bean public MedicationRouteRepository medicationrouteRepository(MedicationRouteJpaRepository repository, MedicationRoutePersistenceMapper mapper) {
        return new MedicationRouteRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterMedicationRouteUseCase registerMedicationRouteUseCase(MedicationRouteRepository r) { return new RegisterMedicationRouteUseCase(r); }
    @Bean public GetMedicationRouteByIdUseCase getMedicationRouteByIdUseCase(MedicationRouteRepository r) { return new GetMedicationRouteByIdUseCase(r); }
    @Bean public ListMedicationRouteUseCase listMedicationRouteUseCase(MedicationRouteRepository r) { return new ListMedicationRouteUseCase(r); }
    @Bean public UpdateMedicationRouteUseCase updateMedicationRouteUseCase(MedicationRouteRepository r) { return new UpdateMedicationRouteUseCase(r); }
    @Bean public DeleteMedicationRouteUseCase deleteMedicationRouteUseCase(MedicationRouteRepository r) { return new DeleteMedicationRouteUseCase(r); }
}
