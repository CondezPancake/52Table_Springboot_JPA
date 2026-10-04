package springboot.domain.country.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.country.model.aggregate.Country;
import springboot.domain.country.model.valueobject.CountryId;

public interface CountryRepository {
    Country save(Country aggregate);
    Optional<Country> findById(CountryId id);
    List<Country> findAll();
    boolean existsByCode(String code);
    void delete(Country aggregate);
}
