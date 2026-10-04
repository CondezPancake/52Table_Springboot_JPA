package springboot.infrastructure.country.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.country.model.aggregate.Country;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.country.port.repository.CountryRepository;
import springboot.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;
import springboot.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;

public class CountryRepositoryAdapter implements CountryRepository {
    private final CountryJpaRepository jpaRepository;
    private final CountryPersistenceMapper mapper;
    public CountryRepositoryAdapter(CountryJpaRepository jpaRepository, CountryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public Country save(Country aggregate) {
        CountryJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<Country> findById(CountryId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<Country> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
    @Override public boolean existsByCode(String code) { return jpaRepository.existsByCodeCountry(code); }
    @Override public void delete(Country aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
