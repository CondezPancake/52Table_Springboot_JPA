package springboot.infrastructure.emailcontact.adapters.out.persistence.mappers;

import springboot.domain.emailcontact.model.aggregate.EmailContact;
import springboot.domain.emailcontact.model.valueobject.EmailContactId;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;

public class EmailContactPersistenceMapper {
    public EmailContactJpaEntity toJpa(EmailContact domain) {
        if (domain == null) { return null; }
        EmailContactJpaEntity jpa = new EmailContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setContactId(domain.contactId().value());
        jpa.setEmail(domain.email());
        jpa.setNotes(domain.notes());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public EmailContact toDomain(EmailContactJpaEntity jpa) {
        if (jpa == null) { return null; }
        return EmailContact.restore(
                new EmailContactId(jpa.getId()),
                new ContactId(jpa.getContactId()),
                jpa.getEmail(),
                jpa.getNotes(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
