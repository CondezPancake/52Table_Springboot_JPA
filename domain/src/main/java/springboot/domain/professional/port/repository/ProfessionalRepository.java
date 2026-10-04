package springboot.domain.professional.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.professional.model.aggregate.Professional;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public interface ProfessionalRepository {
    Professional save(Professional aggregate);
    Optional<Professional> findById(ProfessionalId id);
    List<Professional> findAll();

    void delete(Professional aggregate);
}
