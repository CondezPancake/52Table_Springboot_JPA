package springboot.application.messagetype.usecase;

import java.time.LocalDateTime;

import springboot.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import springboot.domain.messagetype.event.MessageTypeDeletedEvent;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;

public class DeleteMessageTypeUseCase {
    private final MessageTypeRepository repository;
    public DeleteMessageTypeUseCase(MessageTypeRepository repository) { this.repository = repository; }

    public MessageTypeDeletedEvent execute(MessageTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MessageTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new MessageTypeDeletedEvent(id, LocalDateTime.now());
    }
}
