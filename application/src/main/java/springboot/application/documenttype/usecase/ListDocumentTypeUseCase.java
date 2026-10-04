package springboot.application.documenttype.usecase;

import java.util.List;

import springboot.application.documenttype.dto.DocumentTypeResponse;
import springboot.domain.documenttype.port.repository.DocumentTypeRepository;

public class ListDocumentTypeUseCase {
    private final DocumentTypeRepository repository;
    public ListDocumentTypeUseCase(DocumentTypeRepository repository) { this.repository = repository; }

    public List<DocumentTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new DocumentTypeResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.active(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
