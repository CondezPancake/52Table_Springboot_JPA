package springboot.domain.encountermodality.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.encountermodality.model.aggregate.EncounterModality;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;

public interface EncounterModalityRepository {
    EncounterModality save(EncounterModality aggregate);
    Optional<EncounterModality> findById(EncounterModalityId id);
    List<EncounterModality> findAll();
    boolean existsByCode(String code);
    void delete(EncounterModality aggregate);
}
