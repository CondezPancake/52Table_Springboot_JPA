package springboot.domain.patientallergy.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.patientallergy.model.aggregate.PatientAllergy;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;

public interface PatientAllergyRepository {
    PatientAllergy save(PatientAllergy aggregate);
    Optional<PatientAllergy> findById(PatientAllergyId id);
    List<PatientAllergy> findAll();

    void delete(PatientAllergy aggregate);
}
