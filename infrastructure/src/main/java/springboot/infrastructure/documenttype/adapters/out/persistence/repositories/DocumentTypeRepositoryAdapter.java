package springboot.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.documenttype.model.aggregate.DocumentType;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.documenttype.port.repository.DocumentTypeRepository;
import springboot.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;
import springboot.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;

public class DocumentTypeRepositoryAdapter implements DocumentTypeRepository {
    private final DocumentTypeJpaRepository jpaRepository;
    private final DocumentTypePersistenceMapper mapper;
    public DocumentTypeRepositoryAdapter(DocumentTypeJpaRepository jpaRepository, DocumentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public DocumentType save(DocumentType aggregate) {
        DocumentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<DocumentType> findById(DocumentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<DocumentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCode(code); }
    @Override public void delete(DocumentType aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
