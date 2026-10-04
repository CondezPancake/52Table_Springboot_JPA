package springboot.domain.phonecontact.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.phonecontact.model.aggregate.PhoneContact;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;

public interface PhoneContactRepository {
    PhoneContact save(PhoneContact aggregate);
    Optional<PhoneContact> findById(PhoneContactId id);
    List<PhoneContact> findAll();

    void delete(PhoneContact aggregate);
}
