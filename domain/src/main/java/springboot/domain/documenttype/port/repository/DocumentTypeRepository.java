package springboot.domain.documenttype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.documenttype.model.aggregate.DocumentType;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;

public interface DocumentTypeRepository {
    DocumentType save(DocumentType aggregate);
    Optional<DocumentType> findById(DocumentTypeId id);
    List<DocumentType> findAll();
    boolean existsByCode(String code);
    void delete(DocumentType aggregate);
}
