package springboot.domain.diagnosticsystem.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public interface DiagnosticSystemRepository {
    DiagnosticSystem save(DiagnosticSystem aggregate);
    Optional<DiagnosticSystem> findById(DiagnosticSystemId id);
    List<DiagnosticSystem> findAll();
    boolean existsByCode(String code);
    void delete(DiagnosticSystem aggregate);
}
