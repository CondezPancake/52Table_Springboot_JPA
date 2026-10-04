package springboot.domain.emailcontact.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.emailcontact.model.aggregate.EmailContact;
import springboot.domain.emailcontact.model.valueobject.EmailContactId;

public interface EmailContactRepository {
    EmailContact save(EmailContact aggregate);
    Optional<EmailContact> findById(EmailContactId id);
    List<EmailContact> findAll();

    void delete(EmailContact aggregate);
}
