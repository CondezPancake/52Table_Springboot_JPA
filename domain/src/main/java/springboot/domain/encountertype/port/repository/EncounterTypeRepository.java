package springboot.domain.encountertype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.encountertype.model.aggregate.EncounterType;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;

public interface EncounterTypeRepository {
    EncounterType save(EncounterType aggregate);
    Optional<EncounterType> findById(EncounterTypeId id);
    List<EncounterType> findAll();
    boolean existsByCode(String code);
    void delete(EncounterType aggregate);
}
