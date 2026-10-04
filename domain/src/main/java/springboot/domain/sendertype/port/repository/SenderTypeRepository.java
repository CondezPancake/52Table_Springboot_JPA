package springboot.domain.sendertype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.sendertype.model.aggregate.SenderType;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;

public interface SenderTypeRepository {
    SenderType save(SenderType aggregate);
    Optional<SenderType> findById(SenderTypeId id);
    List<SenderType> findAll();

    void delete(SenderType aggregate);
}
