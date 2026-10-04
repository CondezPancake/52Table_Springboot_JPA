package springboot.application.mentalstatusexam.usecase;

import java.util.List;

import springboot.application.mentalstatusexam.dto.MentalStatusExamResponse;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class ListMentalStatusExamUseCase {
    private final MentalStatusExamRepository repository;
    public ListMentalStatusExamUseCase(MentalStatusExamRepository repository) { this.repository = repository; }

    public List<MentalStatusExamResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new MentalStatusExamResponse(
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
                                aggregate.createdAt()))
                .toList();
    }
}
