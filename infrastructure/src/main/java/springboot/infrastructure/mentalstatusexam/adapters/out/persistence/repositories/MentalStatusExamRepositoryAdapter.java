package springboot.infrastructure.mentalstatusexam.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import springboot.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;
import springboot.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;

public class MentalStatusExamRepositoryAdapter implements MentalStatusExamRepository {
    private final MentalStatusExamJpaRepository jpaRepository;
    private final MentalStatusExamPersistenceMapper mapper;
    public MentalStatusExamRepositoryAdapter(MentalStatusExamJpaRepository jpaRepository, MentalStatusExamPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public MentalStatusExam save(MentalStatusExam aggregate) {
        MentalStatusExamJpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<MentalStatusExam> findById(MentalStatusExamId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<MentalStatusExam> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override public void delete(MentalStatusExam aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
