package springboot.application.documenttype.usecase;

import java.time.LocalDateTime;

import springboot.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import springboot.domain.documenttype.event.DocumentTypeDeletedEvent;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.documenttype.port.repository.DocumentTypeRepository;

public class DeleteDocumentTypeUseCase {
    private final DocumentTypeRepository repository;
    public DeleteDocumentTypeUseCase(DocumentTypeRepository repository) { this.repository = repository; }

    public DocumentTypeDeletedEvent execute(DocumentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DocumentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new DocumentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
