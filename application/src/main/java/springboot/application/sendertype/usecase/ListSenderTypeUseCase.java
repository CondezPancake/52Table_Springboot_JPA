package springboot.application.sendertype.usecase;

import java.util.List;

import springboot.application.sendertype.dto.SenderTypeResponse;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;

public class ListSenderTypeUseCase {
    private final SenderTypeRepository repository;
    public ListSenderTypeUseCase(SenderTypeRepository repository) { this.repository = repository; }

    public List<SenderTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SenderTypeResponse(
                                aggregate.id().value(),
                                aggregate.nameType(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
