package springboot.application.mentalstatusexam.usecase;

import java.time.LocalDateTime;

import springboot.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import springboot.domain.mentalstatusexam.event.MentalStatusExamDeletedEvent;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class DeleteMentalStatusExamUseCase {
    private final MentalStatusExamRepository repository;
    public DeleteMentalStatusExamUseCase(MentalStatusExamRepository repository) { this.repository = repository; }

    public MentalStatusExamDeletedEvent execute(MentalStatusExamId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MentalStatusExamNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new MentalStatusExamDeletedEvent(id, LocalDateTime.now());
    }
}
