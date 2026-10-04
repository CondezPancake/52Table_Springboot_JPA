package springboot.domain.professionaltype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.professionaltype.model.aggregate.ProfessionalType;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public interface ProfessionalTypeRepository {
    ProfessionalType save(ProfessionalType aggregate);
    Optional<ProfessionalType> findById(ProfessionalTypeId id);
    List<ProfessionalType> findAll();

    void delete(ProfessionalType aggregate);
}
