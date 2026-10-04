package springboot.application.encounter.usecase;

import springboot.application.encounter.command.UpdateEncounterCommand;
import springboot.application.encounter.dto.EncounterResponse;
import springboot.application.encounter.exception.EncounterNotFoundApplicationException;
import springboot.domain.encounter.port.repository.EncounterRepository;

public class UpdateEncounterUseCase {
    private final EncounterRepository repository;
    public UpdateEncounterUseCase(EncounterRepository repository) { this.repository = repository; }

    public EncounterResponse execute(UpdateEncounterCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.clinicalRecordId(),
                command.professionalId(),
                command.encounterTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                command.createdBy(),
                command.updatedBy());
        var saved = repository.save(aggregate);
        return new EncounterResponse(
                saved.id().value(),
                saved.clinicalRecordId().value(),
                saved.professionalId().value(),
                saved.encounterTypeId().value(),
                saved.startedAt(),
                saved.endedAt(),
                saved.reasonForVisit(),
                saved.currentCondition(),
                saved.modalityId().value(),
                saved.statusId().value(),
                saved.createdBy().value(),
                saved.updatedBy().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
