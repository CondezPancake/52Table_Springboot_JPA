package springboot.infrastructure.patientallergy.adapters.out.persistence.mappers;

import springboot.domain.patientallergy.model.aggregate.PatientAllergy;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

public class PatientAllergyPersistenceMapper {
    public PatientAllergyJpaEntity toJpa(PatientAllergy domain) {
        if (domain == null) { return null; }
        PatientAllergyJpaEntity jpa = new PatientAllergyJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setPatientId(domain.patientId().value());
        jpa.setSubstance(domain.substance());
        jpa.setReaction(domain.reaction());
        jpa.setSeverity(domain.severity());
        jpa.setActive(domain.active());
        jpa.setRecordedAt(domain.recordedAt());
        jpa.setRecordedBy(domain.recordedBy().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public PatientAllergy toDomain(PatientAllergyJpaEntity jpa) {
        if (jpa == null) { return null; }
        return PatientAllergy.restore(
                new PatientAllergyId(jpa.getId()),
                new PatientId(jpa.getPatientId()),
                jpa.getSubstance(),
                jpa.getReaction(),
                jpa.getSeverity(),
                jpa.isActive(),
                jpa.getRecordedAt(),
                new ProfessionalId(jpa.getRecordedBy()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
