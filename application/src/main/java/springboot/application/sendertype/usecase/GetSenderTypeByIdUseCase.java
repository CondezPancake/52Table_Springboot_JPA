package springboot.application.sendertype.usecase;

import springboot.application.sendertype.dto.SenderTypeResponse;
import springboot.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;
import springboot.domain.sendertype.port.repository.SenderTypeRepository;

public class GetSenderTypeByIdUseCase {
    private final SenderTypeRepository repository;
    public GetSenderTypeByIdUseCase(SenderTypeRepository repository) { this.repository = repository; }

    public SenderTypeResponse execute(SenderTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SenderTypeNotFoundApplicationException(id.value().toString()));
        return new SenderTypeResponse(
                aggregate.id().value(),
                aggregate.nameType(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
