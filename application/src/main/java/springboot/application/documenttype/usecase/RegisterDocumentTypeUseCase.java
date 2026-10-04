package springboot.application.documenttype.usecase;

import springboot.application.documenttype.command.RegisterDocumentTypeCommand;
import springboot.application.documenttype.dto.DocumentTypeResponse;
import springboot.domain.documenttype.model.aggregate.DocumentType;
import springboot.domain.documenttype.port.repository.DocumentTypeRepository;

public class RegisterDocumentTypeUseCase {
    private final DocumentTypeRepository repository;
    public RegisterDocumentTypeUseCase(DocumentTypeRepository repository) { this.repository = repository; }

    public DocumentTypeResponse execute(RegisterDocumentTypeCommand command) {
        DocumentType aggregate = DocumentType.register(
                command.code(),
                command.name(),
                command.active());
        DocumentType saved = repository.save(aggregate);
        return new DocumentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
