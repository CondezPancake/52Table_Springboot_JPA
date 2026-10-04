package springboot.application.mentalstatusexam.usecase;

import springboot.application.mentalstatusexam.dto.MentalStatusExamResponse;
import springboot.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class GetMentalStatusExamByIdUseCase {
    private final MentalStatusExamRepository repository;
    public GetMentalStatusExamByIdUseCase(MentalStatusExamRepository repository) { this.repository = repository; }

    public MentalStatusExamResponse execute(MentalStatusExamId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MentalStatusExamNotFoundApplicationException(id.value().toString()));
        return new MentalStatusExamResponse(
                aggregate.id().value(),
                aggregate.encounterId().value(),
                aggregate.appearance(),
                aggregate.behavior(),
                aggregate.attitude(),
                aggregate.consciousness(),
                aggregate.orientation(),
                aggregate.attention(),
                aggregate.memory(),
                aggregate.speech(),
                aggregate.mood(),
                aggregate.affect(),
                aggregate.thoughtProcess(),
                aggregate.thoughtContent(),
                aggregate.perception(),
                aggregate.judgment(),
                aggregate.insight(),
                aggregate.psychomotorActivity(),
                aggregate.observations(),
                aggregate.createdBy().value(),
                aggregate.createdAt());
    }
}
