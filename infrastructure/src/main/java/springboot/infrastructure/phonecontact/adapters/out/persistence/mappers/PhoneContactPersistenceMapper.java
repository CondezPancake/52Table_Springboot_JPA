package springboot.infrastructure.phonecontact.adapters.out.persistence.mappers;

import springboot.domain.phonecontact.model.aggregate.PhoneContact;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

public class PhoneContactPersistenceMapper {
    public PhoneContactJpaEntity toJpa(PhoneContact domain) {
        if (domain == null) { return null; }
        PhoneContactJpaEntity jpa = new PhoneContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setContactId(domain.contactId().value());
        jpa.setPhone(domain.phone());
        jpa.setNotes(domain.notes());

        return jpa;
    }

    public PhoneContact toDomain(PhoneContactJpaEntity jpa) {
        if (jpa == null) { return null; }
        return PhoneContact.restore(
                new PhoneContactId(jpa.getId()),
                new ContactId(jpa.getContactId()),
                jpa.getPhone(),
                jpa.getNotes());
    }
}
