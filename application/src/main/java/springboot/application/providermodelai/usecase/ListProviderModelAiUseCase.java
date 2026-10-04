package springboot.application.providermodelai.usecase;

import java.util.List;

import springboot.application.providermodelai.dto.ProviderModelAiResponse;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class ListProviderModelAiUseCase {
    private final ProviderModelAiRepository repository;
    public ListProviderModelAiUseCase(ProviderModelAiRepository repository) { this.repository = repository; }

    public List<ProviderModelAiResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ProviderModelAiResponse(
                                aggregate.id().value(),
                                aggregate.nameProviderAi(),
                                aggregate.razonSocial(),
                                aggregate.sitioWeb(),
                                aggregate.active(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
