package springboot.application.encountertype.usecase;

import java.util.List;

import springboot.application.encountertype.dto.EncounterTypeResponse;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;

public class ListEncounterTypeUseCase {
    private final EncounterTypeRepository repository;
    public ListEncounterTypeUseCase(EncounterTypeRepository repository) { this.repository = repository; }

    public List<EncounterTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new EncounterTypeResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.active(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
