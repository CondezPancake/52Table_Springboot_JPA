package springboot.infrastructure.contact.adapters.out.persistence.mappers;

import springboot.domain.contact.model.aggregate.Contact;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;

public class ContactPersistenceMapper {
    public ContactJpaEntity toJpa(Contact domain) {
        if (domain == null) { return null; }
        ContactJpaEntity jpa = new ContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setFullName(domain.fullName());
        jpa.setEmail(domain.email());
        jpa.setNotes(domain.notes());
        jpa.setCityId(domain.cityId().value());
        jpa.setCreatedBy(domain.createdBy().value());
        jpa.setUpdatedBy(domain.updatedBy() == null ? null : domain.updatedBy().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public Contact toDomain(ContactJpaEntity jpa) {
        if (jpa == null) { return null; }
        return Contact.restore(
                new ContactId(jpa.getId()),
                jpa.getFullName(),
                jpa.getEmail(),
                jpa.getNotes(),
                new CityMunicipalityId(jpa.getCityId()),
                new ProfessionalId(jpa.getCreatedBy()),
                jpa.getUpdatedBy() == null ? null : new ProfessionalId(jpa.getUpdatedBy()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
