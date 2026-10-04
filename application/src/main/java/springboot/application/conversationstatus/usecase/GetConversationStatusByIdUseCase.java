package springboot.application.conversationstatus.usecase;

import springboot.application.conversationstatus.dto.ConversationStatusResponse;
import springboot.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class GetConversationStatusByIdUseCase {
    private final ConversationStatusRepository repository;
    public GetConversationStatusByIdUseCase(ConversationStatusRepository repository) { this.repository = repository; }

    public ConversationStatusResponse execute(ConversationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationStatusNotFoundApplicationException(id.value().toString()));
        return new ConversationStatusResponse(
                aggregate.id().value(),
                aggregate.nameStatus(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
