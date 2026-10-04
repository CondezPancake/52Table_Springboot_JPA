package springboot.domain.consenttype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.consenttype.model.aggregate.ConsentType;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;

public interface ConsentTypeRepository {
    ConsentType save(ConsentType aggregate);
    Optional<ConsentType> findById(ConsentTypeId id);
    List<ConsentType> findAll();
    boolean existsByCode(String code);
    void delete(ConsentType aggregate);
}
