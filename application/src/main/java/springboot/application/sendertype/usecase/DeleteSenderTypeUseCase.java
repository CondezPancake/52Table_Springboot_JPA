package springboot.application.sendertype.usecase;

import java.time.LocalDateTime;

import springboot.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import springboot.domain.sendertype.event.SenderTypeDeletedEvent;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;

public class DeleteSenderTypeUseCase {
    private final SenderTypeRepository repository;
    public DeleteSenderTypeUseCase(SenderTypeRepository repository) { this.repository = repository; }

    public SenderTypeDeletedEvent execute(SenderTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SenderTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SenderTypeDeletedEvent(id, LocalDateTime.now());
    }
}
