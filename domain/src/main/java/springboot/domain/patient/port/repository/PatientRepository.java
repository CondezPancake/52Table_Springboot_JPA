package springboot.domain.patient.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.patient.model.aggregate.Patient;
import springboot.domain.patient.model.valueobject.PatientId;

public interface PatientRepository {
    Patient save(Patient aggregate);
    Optional<Patient> findById(PatientId id);
    List<Patient> findAll();

    void delete(Patient aggregate);
}
