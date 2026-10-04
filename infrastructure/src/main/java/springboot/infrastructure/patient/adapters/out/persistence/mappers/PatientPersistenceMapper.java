package springboot.infrastructure.patient.adapters.out.persistence.mappers;

import springboot.domain.patient.model.aggregate.Patient;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;

public class PatientPersistenceMapper {
    public PatientJpaEntity toJpa(Patient domain) {
        if (domain == null) { return null; }
        PatientJpaEntity jpa = new PatientJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setDocumentTypeId(domain.documentTypeId().value());
        jpa.setDocumentNumber(domain.documentNumber());
        jpa.setFirstName(domain.firstName());
        jpa.setMiddleName(domain.middleName());
        jpa.setLastName(domain.lastName());
        jpa.setSecondLastName(domain.secondLastName());
        jpa.setBirthDate(domain.birthDate());
        jpa.setBiologicalSexId(domain.biologicalSexId().value());
        jpa.setGenderIdentityId(domain.genderIdentityId().value());
        jpa.setEmail(domain.email());
        jpa.setPhone(domain.phone());
        jpa.setAddress(domain.address());
        jpa.setActive(domain.active());
        jpa.setCreatedBy(domain.createdBy() == null ? null : domain.createdBy().value());
        jpa.setUpdatedBy(domain.updatedBy() == null ? null : domain.updatedBy().value());
        jpa.setCityId(domain.cityId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Patient toDomain(PatientJpaEntity jpa) {
        if (jpa == null) { return null; }
        return Patient.restore(
                new PatientId(jpa.getId()),
                new DocumentTypeId(jpa.getDocumentTypeId()),
                jpa.getDocumentNumber(),
                jpa.getFirstName(),
                jpa.getMiddleName(),
                jpa.getLastName(),
                jpa.getSecondLastName(),
                jpa.getBirthDate(),
                new GenderId(jpa.getBiologicalSexId()),
                new GenderId(jpa.getGenderIdentityId()),
                jpa.getEmail(),
                jpa.getPhone(),
                jpa.getAddress(),
                jpa.isActive(),
                jpa.getCreatedBy() == null ? null : new ProfessionalId(jpa.getCreatedBy()),
                jpa.getUpdatedBy() == null ? null : new ProfessionalId(jpa.getUpdatedBy()),
                new CityMunicipalityId(jpa.getCityId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
