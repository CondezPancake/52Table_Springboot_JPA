package springboot.application.aimodel.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.aimodel.exception.AiModelNotFoundApplicationException;
import springboot.domain.aimodel.event.AiModelDeletedEvent;
import springboot.domain.aimodel.model.aggregate.AiModel;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.aimodel.port.repository.AiModelRepository;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

class DeleteAiModelUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        AiModel aggregate = AiModel.register(
                ProviderModelAiId.generate(),
                "Model One",
                "model-one",
                new java.math.BigDecimal("0.00000100"),
                new java.math.BigDecimal("0.00000200"),
                4096,
                128000,
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        AiModelDeletedEvent event = new DeleteAiModelUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AiModelNotFoundApplicationException.class,
                () -> new DeleteAiModelUseCase(repository).execute(AiModelId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements AiModelRepository {
        private final AiModel aggregate; private AiModel deletedAggregate;
        private FakeRepository(AiModel aggregate) { this.aggregate = aggregate; }
        @Override public AiModel save(AiModel value) { return value; }
        @Override public Optional<AiModel> findById(AiModelId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<AiModel> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(AiModel value) { deletedAggregate = value; }
        private AiModel deletedAggregate() { return deletedAggregate; }
    }
}
