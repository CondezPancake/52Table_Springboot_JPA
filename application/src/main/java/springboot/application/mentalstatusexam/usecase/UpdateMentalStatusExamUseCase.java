package springboot.application.mentalstatusexam.usecase;

import springboot.application.mentalstatusexam.command.UpdateMentalStatusExamCommand;
import springboot.application.mentalstatusexam.dto.MentalStatusExamResponse;
import springboot.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class UpdateMentalStatusExamUseCase {
    private final MentalStatusExamRepository repository;
    public UpdateMentalStatusExamUseCase(MentalStatusExamRepository repository) { this.repository = repository; }

    public MentalStatusExamResponse execute(UpdateMentalStatusExamCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MentalStatusExamNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.encounterId(),
                command.appearance(),
                command.behavior(),
                command.attitude(),
                command.consciousness(),
                command.orientation(),
                command.attention(),
                command.memory(),
                command.speech(),
                command.mood(),
                command.affect(),
                command.thoughtProcess(),
                command.thoughtContent(),
                command.perception(),
                command.judgment(),
                command.insight(),
                command.psychomotorActivity(),
                command.observations(),
                command.createdBy());
        var saved = repository.save(aggregate);
        return new MentalStatusExamResponse(
                saved.id().value(),
                saved.encounterId().value(),
                saved.appearance(),
                saved.behavior(),
                saved.attitude(),
                saved.consciousness(),
                saved.orientation(),
                saved.attention(),
                saved.memory(),
                saved.speech(),
                saved.mood(),
                saved.affect(),
                saved.thoughtProcess(),
                saved.thoughtContent(),
                saved.perception(),
                saved.judgment(),
                saved.insight(),
                saved.psychomotorActivity(),
                saved.observations(),
                saved.createdBy().value(),
                saved.createdAt());
    }
}
