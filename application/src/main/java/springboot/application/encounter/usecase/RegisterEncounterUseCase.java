package springboot.application.encounter.usecase;

import springboot.application.encounter.command.RegisterEncounterCommand;
import springboot.application.encounter.dto.EncounterResponse;
import springboot.domain.encounter.model.aggregate.Encounter;
import springboot.domain.encounter.port.repository.EncounterRepository;

public class RegisterEncounterUseCase {
    private final EncounterRepository repository;
    public RegisterEncounterUseCase(EncounterRepository repository) { this.repository = repository; }

    public EncounterResponse execute(RegisterEncounterCommand command) {
        Encounter aggregate = Encounter.register(
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
        Encounter saved = repository.save(aggregate);
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
