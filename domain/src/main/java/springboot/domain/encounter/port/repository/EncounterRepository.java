package springboot.domain.encounter.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.encounter.model.aggregate.Encounter;
import springboot.domain.encounter.model.valueobject.EncounterId;

public interface EncounterRepository {
    Encounter save(Encounter aggregate);
    Optional<Encounter> findById(EncounterId id);
    List<Encounter> findAll();

    void delete(Encounter aggregate);
}
