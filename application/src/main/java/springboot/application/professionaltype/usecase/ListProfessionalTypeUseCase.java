package springboot.application.professionaltype.usecase;

import java.util.List;

import springboot.application.professionaltype.dto.ProfessionalTypeResponse;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class ListProfessionalTypeUseCase {
    private final ProfessionalTypeRepository repository;
    public ListProfessionalTypeUseCase(ProfessionalTypeRepository repository) { this.repository = repository; }

    public List<ProfessionalTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ProfessionalTypeResponse(
                                aggregate.id().value(),
                                aggregate.name(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
