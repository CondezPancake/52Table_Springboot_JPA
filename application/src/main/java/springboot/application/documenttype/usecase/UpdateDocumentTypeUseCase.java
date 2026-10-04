package springboot.application.documenttype.usecase;

import springboot.application.documenttype.command.UpdateDocumentTypeCommand;
import springboot.application.documenttype.dto.DocumentTypeResponse;
import springboot.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import springboot.domain.documenttype.port.repository.DocumentTypeRepository;

public class UpdateDocumentTypeUseCase {
    private final DocumentTypeRepository repository;
    public UpdateDocumentTypeUseCase(DocumentTypeRepository repository) { this.repository = repository; }

    public DocumentTypeResponse execute(UpdateDocumentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DocumentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new DocumentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
