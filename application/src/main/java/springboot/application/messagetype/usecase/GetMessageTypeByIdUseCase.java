package springboot.application.messagetype.usecase;

import springboot.application.messagetype.dto.MessageTypeResponse;
import springboot.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;

public class GetMessageTypeByIdUseCase {
    private final MessageTypeRepository repository;
    public GetMessageTypeByIdUseCase(MessageTypeRepository repository) { this.repository = repository; }

    public MessageTypeResponse execute(MessageTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MessageTypeNotFoundApplicationException(id.value().toString()));
        return new MessageTypeResponse(
                aggregate.id().value(),
                aggregate.nameType(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
