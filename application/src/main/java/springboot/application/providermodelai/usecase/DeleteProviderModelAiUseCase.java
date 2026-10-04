package springboot.application.providermodelai.usecase;

import java.time.LocalDateTime;

import springboot.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import springboot.domain.providermodelai.event.ProviderModelAiDeletedEvent;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class DeleteProviderModelAiUseCase {
    private final ProviderModelAiRepository repository;
    public DeleteProviderModelAiUseCase(ProviderModelAiRepository repository) { this.repository = repository; }

    public ProviderModelAiDeletedEvent execute(ProviderModelAiId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProviderModelAiNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ProviderModelAiDeletedEvent(id, LocalDateTime.now());
    }
}
