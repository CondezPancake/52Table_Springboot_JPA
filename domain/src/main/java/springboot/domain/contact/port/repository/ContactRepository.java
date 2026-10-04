package springboot.domain.contact.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.contact.model.aggregate.Contact;
import springboot.domain.contact.model.valueobject.ContactId;

public interface ContactRepository {
    Contact save(Contact aggregate);
    Optional<Contact> findById(ContactId id);
    List<Contact> findAll();

    void delete(Contact aggregate);
}
