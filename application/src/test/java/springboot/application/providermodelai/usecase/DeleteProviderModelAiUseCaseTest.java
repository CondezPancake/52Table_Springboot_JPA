package springboot.application.providermodelai.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import springboot.domain.providermodelai.event.ProviderModelAiDeletedEvent;
import springboot.domain.providermodelai.model.aggregate.ProviderModelAi;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;
import springboot.domain.providermodelai.port.repository.ProviderModelAiRepository;


class DeleteProviderModelAiUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ProviderModelAi aggregate = ProviderModelAi.register(
                "OpenAI",
                "AI Provider Inc.",
                "https://example.com",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        ProviderModelAiDeletedEvent event = new DeleteProviderModelAiUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ProviderModelAiNotFoundApplicationException.class,
                () -> new DeleteProviderModelAiUseCase(repository).execute(ProviderModelAiId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ProviderModelAiRepository {
        private final ProviderModelAi aggregate; private ProviderModelAi deletedAggregate;
        private FakeRepository(ProviderModelAi aggregate) { this.aggregate = aggregate; }
        @Override public ProviderModelAi save(ProviderModelAi value) { return value; }
        @Override public Optional<ProviderModelAi> findById(ProviderModelAiId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ProviderModelAi> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ProviderModelAi value) { deletedAggregate = value; }
        private ProviderModelAi deletedAggregate() { return deletedAggregate; }
    }
}
