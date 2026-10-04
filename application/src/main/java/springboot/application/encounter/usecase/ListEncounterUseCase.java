package springboot.application.encounter.usecase;

import java.util.List;

import springboot.application.encounter.dto.EncounterResponse;
import springboot.domain.encounter.port.repository.EncounterRepository;

public class ListEncounterUseCase {
    private final EncounterRepository repository;
    public ListEncounterUseCase(EncounterRepository repository) { this.repository = repository; }

    public List<EncounterResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new EncounterResponse(
                                aggregate.id().value(),
                                aggregate.clinicalRecordId().value(),
                                aggregate.professionalId().value(),
                                aggregate.encounterTypeId().value(),
                                aggregate.startedAt(),
                                aggregate.endedAt(),
                                aggregate.reasonForVisit(),
                                aggregate.currentCondition(),
                                aggregate.modalityId().value(),
                                aggregate.statusId().value(),
                                aggregate.createdBy().value(),
                                aggregate.updatedBy().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
