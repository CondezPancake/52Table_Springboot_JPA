package springboot.application.encountermodality.usecase;

import java.util.List;

import springboot.application.encountermodality.dto.EncounterModalityResponse;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;

public class ListEncounterModalityUseCase {
    private final EncounterModalityRepository repository;
    public ListEncounterModalityUseCase(EncounterModalityRepository repository) { this.repository = repository; }

    public List<EncounterModalityResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new EncounterModalityResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.active(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
