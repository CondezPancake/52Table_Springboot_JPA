package springboot.application.messagetype.usecase;

import java.util.List;

import springboot.application.messagetype.dto.MessageTypeResponse;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;

public class ListMessageTypeUseCase {
    private final MessageTypeRepository repository;
    public ListMessageTypeUseCase(MessageTypeRepository repository) { this.repository = repository; }

    public List<MessageTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new MessageTypeResponse(
                                aggregate.id().value(),
                                aggregate.nameType(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
