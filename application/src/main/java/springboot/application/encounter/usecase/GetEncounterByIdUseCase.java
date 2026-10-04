package springboot.application.encounter.usecase;

import springboot.application.encounter.dto.EncounterResponse;
import springboot.application.encounter.exception.EncounterNotFoundApplicationException;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.encounter.port.repository.EncounterRepository;

public class GetEncounterByIdUseCase {
    private final EncounterRepository repository;
    public GetEncounterByIdUseCase(EncounterRepository repository) { this.repository = repository; }

    public EncounterResponse execute(EncounterId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterNotFoundApplicationException(id.value().toString()));
        return new EncounterResponse(
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
                aggregate.updatedAt());
    }
}
