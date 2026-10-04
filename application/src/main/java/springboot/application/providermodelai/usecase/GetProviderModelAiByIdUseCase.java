package springboot.application.providermodelai.usecase;

import springboot.application.providermodelai.dto.ProviderModelAiResponse;
import springboot.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class GetProviderModelAiByIdUseCase {
    private final ProviderModelAiRepository repository;
    public GetProviderModelAiByIdUseCase(ProviderModelAiRepository repository) { this.repository = repository; }

    public ProviderModelAiResponse execute(ProviderModelAiId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProviderModelAiNotFoundApplicationException(id.value().toString()));
        return new ProviderModelAiResponse(
                aggregate.id().value(),
                aggregate.nameProviderAi(),
                aggregate.razonSocial(),
                aggregate.sitioWeb(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
