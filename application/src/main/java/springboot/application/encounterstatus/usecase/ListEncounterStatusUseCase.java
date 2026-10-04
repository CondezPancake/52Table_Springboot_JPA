package springboot.application.encounterstatus.usecase;

import java.util.List;

import springboot.application.encounterstatus.dto.EncounterStatusResponse;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class ListEncounterStatusUseCase {
    private final EncounterStatusRepository repository;
    public ListEncounterStatusUseCase(EncounterStatusRepository repository) { this.repository = repository; }

    public List<EncounterStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new EncounterStatusResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.active(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
